package com.adrianlopez.music_recognition.viewmodels

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeOnce(onChangedHandler: (T) -> Unit) {
    val observer = OneTimeObserver(handler = onChangedHandler)
    observe(observer, observer)
}