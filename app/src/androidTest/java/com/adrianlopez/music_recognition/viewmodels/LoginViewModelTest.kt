package com.adrianlopez.music_recognition.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class LoginViewModelTest {
    lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        viewModel = LoginViewModel()
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun  emptyEmailAndPassword_ReturnsFalse() {
        //Given empty email and password values
        val email = ""
        val password = ""

        //When input email and password
        viewModel.emailTextChanged(email)
        viewModel.passwordTextChanged(password)

        //Then Login should be disabled
        viewModel.isLoginEnabled.observeOnce { isEnabled -> assertThat(isEnabled, `is`(false)) }
    }

    @Test
    fun inputInvalidEmailAndValidPassword_ReturnsFalse() {
        //Given invalid email and valid password
        val email = "adrian"
        val password = "12345678"

        //When input email and password
        viewModel.emailTextChanged(email)
        viewModel.passwordTextChanged(password)

        //Then Login should be disabled
        viewModel.isLoginEnabled.observeOnce { isEnabled -> assertThat(isEnabled, `is`(false)) }
    }

    @Test
    fun inputValidEmailAndInvalidPassword_ReturnsFalse() {
        //Given valid email and invalid password
        val email = "adrian@gmail.com"
        val password = "12345"

        //When input email and password
        viewModel.emailTextChanged(email)
        viewModel.passwordTextChanged(password)

        //Then Login should be disabled
        viewModel.isLoginEnabled.observeOnce { isEnabled -> assertThat(isEnabled, `is`(false)) }
    }

    @Test
    fun  inputValidEmailAndValidPassword_ReturnsTrue() {
        //Given email and password valid values
        val email = "adrian@gmail.com"
        val password = "12345678"

        //When input email and password
        viewModel.emailTextChanged(email)
        viewModel.passwordTextChanged(password)

        //Then Login should be enabled
        viewModel.isLoginEnabled.observeOnce { isEnabled -> assertThat(isEnabled, `is`(true)) }
    }

}

