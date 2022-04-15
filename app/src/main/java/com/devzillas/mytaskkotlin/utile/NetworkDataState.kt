package com.devzillas.mytaskkotlin.utile

sealed class NetworkDataState<T>(
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T) : NetworkDataState<T>(data)
    class Error<T>(message: String?, data: T? = null) : NetworkDataState<T>(data, message)
    class Loading<T> : NetworkDataState<T>()
}
