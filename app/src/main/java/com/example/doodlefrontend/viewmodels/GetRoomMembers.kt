package com.example.doodlefrontend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlefrontend.repository.GetRoomMembers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class GetRoomMembers @Inject constructor(
    val getRoomMembers: GetRoomMembers
) : ViewModel(){

    private var _listMembers = MutableStateFlow<List<String>>(listOf())
    var listMembers = _listMembers.asStateFlow()


    init {
        viewModelScope.launch {
            _listMembers.value = getDetails()

        }
    }

    private suspend fun getDetails() : List<String>{

        val resp = getRoomMembers.getRoomMembers()
        return resp.body() ?: listOf()

    }


}