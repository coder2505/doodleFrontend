package com.example.doodlefrontend.viewmodels

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.doodlefrontend.services.NameUpload
import com.example.doodlefrontend.services.UploadNamePost
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SubmitName() : ViewModel() {

    val events = MutableSharedFlow<UIevents>()

    fun uploadName(name: String, uploadNamePost: UploadNamePost) {

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