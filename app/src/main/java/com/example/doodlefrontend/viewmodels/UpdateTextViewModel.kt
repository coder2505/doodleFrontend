package com.example.doodlefrontend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlefrontend.repository.UpdateNameRepo
import com.example.doodlefrontend.views.HomeScreen.HomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateTextViewModel @Inject constructor(
    val updateNameRepo: UpdateNameRepo
) : ViewModel() {

    val sharedFlow = MutableSharedFlow<HomeScreen>()

    fun update(payload: String) {

        viewModelScope.launch {

            val resp =
                updateNameRepo.uploadName(payload = payload)

            if (resp.isSuccessful) {

                sharedFlow.emit(HomeScreen.Success)

            } else {

                sharedFlow.emit(HomeScreen.ShowSnackBar("Failed to update payload"))

            }

        }

    }

}
