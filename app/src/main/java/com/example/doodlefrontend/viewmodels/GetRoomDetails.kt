package com.example.doodlefrontend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlefrontend.repository.GetRoomDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetRoomDetailsViewModel @Inject constructor(
    val getRoomDetails: GetRoomDetails
)  : ViewModel() {

    private val _roomName = MutableStateFlow("")
    private val _roomCode = MutableStateFlow(0)

    val roomName : StateFlow<String> = _roomName.asStateFlow()
    val roomCode : StateFlow<Int> = _roomCode.asStateFlow()

    init {
        viewModelScope.launch {
            val details = getDetails()
            _roomName.value = details.getValue("roomName")
            _roomCode.value = details.getValue("roomCode").toInt()

        }
    }

    suspend fun getDetails() : Map<String, String>{
        return getRoomDetails.getRoomDetails().body() ?: mapOf()
    }


}