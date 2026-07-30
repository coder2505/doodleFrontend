package com.example.doodlefrontend.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlefrontend.repository.NameUpload
import com.example.doodlefrontend.repository.UploadNamePost
import com.google.firebase.Firebase
import com.google.firebase.installations.installations
import com.google.firebase.messaging.messaging
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@HiltViewModel
class SubmitName @Inject constructor(
    private var uploadNamePost: UploadNamePost,
) : ViewModel() {

    val events = MutableSharedFlow<UIevents>()

    fun uploadName(name: String) {

        viewModelScope.launch {

            uploadNamePost.sharedFlow.collect { event ->

                when (event) {
                    is NameUpload.Success -> events.emit(UIevents.navigateToJoinScreen)
                    is NameUpload.Error -> events.emit(
                        UIevents.ShowSnackBar(
                            event.error
                        )
                    )
                }

            }

        }

        if (name.isEmpty()) {

            viewModelScope.launch {
                events.emit(UIevents.ShowSnackBar("Name Cannot be Empty"))
            }

        } else {

            try {

                Firebase.installations.id.addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("FCM", "Fetching FCM FID failed", task.exception)
                        return@addOnCompleteListener
                    }

                    val fid = task.result ?: "error_token"
                    Log.d("FCM", "FCM FID: $fid")

                    runBlocking {
                        uploadNamePost.uploadName(name, fid as String)
                    }
                }

            } catch (e: Exception) {
                Log.e("Installations", "Unable to get Installation ID", e)
            }

        }
    }

}

sealed class UIevents {
    class ShowSnackBar(val name: String) : UIevents()
    object navigateToJoinScreen : UIevents()
}