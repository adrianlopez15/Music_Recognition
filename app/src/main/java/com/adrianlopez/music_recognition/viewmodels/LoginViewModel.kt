package com.adrianlopez.music_recognition.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn

class LoginViewModel : ViewModel() {
    /**Email and password values store in MutableStateFlow */
    private var _email = MutableStateFlow("")
    private var _password = MutableStateFlow("")

    /**Sets Live Data using flow combine with [_email] and [_password]*/
    val isLoginEnabled : LiveData<Boolean> by lazy {
        combine(_email, _password) { email, password ->
            val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            val isPasswordValid = password.length >= 8

            isEmailValid and isPasswordValid
        }.flowOn(Dispatchers.Default).asLiveData()
    }

    /** Sets value of _email to [email]*/
    fun emailTextChanged(email: String) {
        _email.tryEmit(email)
    }

    /**Sets value of _password to [password] */
    fun passwordTextChanged(password: String) {
        _password.tryEmit(password)
    }
}