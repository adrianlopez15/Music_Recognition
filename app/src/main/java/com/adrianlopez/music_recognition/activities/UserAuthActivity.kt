package com.adrianlopez.music_recognition.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adrianlopez.music_recognition.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserAuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_auth)
    }
}