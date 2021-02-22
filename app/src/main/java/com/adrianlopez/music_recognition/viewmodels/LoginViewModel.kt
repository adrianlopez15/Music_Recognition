package com.adrianlopez.music_recognition.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class LoginViewModel : ViewModel() {
    /**Email and password values store in MutableStateFlow */
    private var _email = MutableStateFlow("")
    private var _password = MutableStateFlow("")

    /** Sets value of _email to [email]*/
    fun emailTextChanged(email: String) {
        _email.value = email
    }

    /**Sets value of _password to [password] */
    fun passwordTextChanged(password: String) {
        _password.value = password
    }

    /**Returns a boolean value using flow combine with [_email] and [_password]*/
    var isLoginEnabled: Flow<Boolean> = combine(_email, _password) { email, password ->
        val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length > 8

        return@combine isEmailValid and isPasswordValid
    }

}