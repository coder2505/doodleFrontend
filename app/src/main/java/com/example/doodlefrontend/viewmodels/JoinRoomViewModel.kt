package com.example.doodlefrontend.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlefrontend.model.backendResponse.JoinRoomResponse
import com.example.doodlefrontend.repository.JoinRoomRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class JoinRoomViewModel @Inject constructor(
    val joinRoomRepo: JoinRoomRepo
) : ViewModel() {

    val sharedFlow = MutableSharedFlow<JoinRoomUIEvents>()

    fun joinRoom(roomId: String) {
        viewModelScope.launch {
            try {
                val resp = joinRoomRepo.join(roomId)
                if (resp.isSuccessful) {
                    sharedFlow.emit(JoinRoomUIEvents.Success)
                } else {
                    val errorMsg = resp.errorBody()?.string() ?: "Failed to join room"
                    sharedFlow.emit(JoinRoomUIEvents.ShowSnackBar(errorMsg))
                }
            } catch (e: Exception) {
                sharedFlow.emit(JoinRoomUIEvents.ShowSnackBar(e.localizedMessage ?: "Network error occurred"))
            }
        }
    }


}

sealed class JoinRoomUIEvents {

    class ShowSnackBar(val message: String) : JoinRoomUIEvents()
    object Success : JoinRoomUIEvents()

}