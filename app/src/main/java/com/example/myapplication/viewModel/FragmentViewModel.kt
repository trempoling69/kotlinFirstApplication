package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.model.MessageResponse

class FragmentViewModel : ViewModel() {
    private val _messages = MutableLiveData<List<String>>(emptyList())
    val messages: LiveData<List<String>> = _messages

    fun addMessage(newMessage: String) {
        _messages.value = (_messages.value ?: emptyList()) + newMessage
    }

    override suspend fun  getApiMessage(): List<MessageResponse>{
        val response = RetrofitInstance
    }
}