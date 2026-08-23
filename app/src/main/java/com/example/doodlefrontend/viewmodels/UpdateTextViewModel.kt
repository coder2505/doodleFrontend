package com.example.doodlefrontend.viewmodels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlefrontend.repository.UpdateNameRepo
import com.example.doodlefrontend.utils.SharedPrefManager
import com.example.doodlefrontend.views.HomeScreen.HomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class UpdateTextViewModel @Inject constructor(
    val updateNameRepo: UpdateNameRepo
) : ViewModel() {

    val sharedFlowUpdateName = MutableSharedFlow<HomeScreen>(extraBufferCapacity = 1)
    private val _doodleText = MutableStateFlow<String>("")
    val doodleText = _doodleText.asStateFlow()

    init {
        _doodleText.value = SharedPrefManager.getText()
    }

    fun update(payload: String, color : String, font : String) {

        viewModelScope.launch {

            val resp =
                updateNameRepo.uploadName(payload = payload, background = color, font = font)

            if (resp.isSuccessful) {
                Log.d("update view model","success babe wtf")
                _doodleText.value = payload
                sharedFlowUpdateName.emit(HomeScreen.Success)

            } else {

                sharedFlowUpdateName.emit(HomeScreen.ShowSnackBar("Failed to update payload"))

            }

        }

    }

}
