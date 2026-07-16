package com.example.doodlefrontend.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlefrontend.repository.NameUpload
import com.example.doodlefrontend.repository.UploadNamePost
import com.google.android.gms.tasks.Task
import com.google.firebase.installations.FirebaseInstallations
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@HiltViewModel
class SubmitName @Inject constructor(
    private var uploadNamePost: UploadNamePost,
) : ViewModel() {

    val events = MutableSharedFlow<UIevents>()

    fun uploadName (name: String) {

        viewModelScope.launch {

            uploadNamePost.sharedFlow.collect { event ->

                when(event){
                    is NameUpload.Success -> events.emit(UIevents.navigateToJoinScreen)
                    is NameUpload.Error -> events.emit(UIevents.ShowSnackBar(event.error))
                }

            }

        }

        if (name.isEmpty()) {

            viewModelScope.launch {
                events.emit(UIevents.ShowSnackBar("Name Cannot be Empty"))
            }

        } else {

            viewModelScope.launch {
                try {
                    val installationId = FirebaseInstallations.getInstance().id.await()
                    Log.d("Installations", "Installation ID: $installationId")

                    uploadNamePost.uploadName(name, installationId)

                } catch (e: Exception) {
                    Log.e("Installations", "Unable to get Installation ID", e)
                }
            }

        }
    }

}

sealed class UIevents {
    class ShowSnackBar(val name : String) : UIevents()
    object navigateToJoinScreen : UIevents()
}