package com.example.doodlefrontend.viewmodels

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlefrontend.repository.NameUpload
import com.example.doodlefrontend.repository.UploadNamePost
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


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

                uploadNamePost.uploadName(name)

            }

        }
    }

}

sealed class UIevents {
    class ShowSnackBar(val name : String) : UIevents()
    object navigateToJoinScreen : UIevents()
}