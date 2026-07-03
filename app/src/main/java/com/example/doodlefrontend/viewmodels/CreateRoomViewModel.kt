package com.example.doodlefrontend.viewmodels

import android.os.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlefrontend.repository.CreateRoomRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateRoomViewModel @Inject constructor(
    var createRoomRepo: CreateRoomRepo
) : ViewModel() {

    var sharedFlow = MutableSharedFlow<UIEventsCreateRoom>()
    private var roomCode = MutableStateFlow("")
    val uiState: StateFlow<String> = roomCode


    fun createRoom(roomName: String) {

        viewModelScope.launch {

            val resp = createRoomRepo.createRoom(roomName)

            if (resp.isSuccessful) {

                roomCode.value = resp.body()?.roomId.toString()
                sharedFlow.emit(
                    UIEventsCreateRoom.roomCreated(resp.body()?.roomId.toString())
                )


            } else {

                viewModelScope.launch {
                    sharedFlow.emit(UIEventsCreateRoom.ShowSnackBar("Error in room creation ${resp.errorBody()}"))
                }
            }

        }

    }


}

sealed class UIEventsCreateRoom{
        class ShowSnackBar(val message : String) : UIEventsCreateRoom()
        class roomCreated(val roomId : String) : UIEventsCreateRoom()
}
