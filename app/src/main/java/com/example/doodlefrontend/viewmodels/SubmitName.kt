package com.example.doodlefrontend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.doodlefrontend.services.UploadNamePost
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SubmitName : ViewModel() {

    val events = MutableSharedFlow<UIevents>()

    fun uploadName(name: String) {

        val u = UploadNamePost()

        if (name.isEmpty()) {

            viewModelScope.launch {
                events.emit(UIevents.ShowSnackBar())
            }

        } else {

            viewModelScope.launch {

                u.uploadName(name)
                events.emit(UIevents.navigateToJoinScreen)


            }

        }
    }

}

sealed class UIevents {
    class ShowSnackBar : UIevents()
    object navigateToJoinScreen : UIevents()
}