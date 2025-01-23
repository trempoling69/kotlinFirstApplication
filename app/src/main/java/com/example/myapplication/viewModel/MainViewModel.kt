package com.example.myapplication.viewModel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var email = mutableStateOf("")
        private set
    var password =  mutableStateOf("")
        private set

    fun onMailChanged(newMail: String){
        email.value = newMail
    }

    fun onPasswordChanged(newPassword: String){
        password.value = newPassword
    }
}