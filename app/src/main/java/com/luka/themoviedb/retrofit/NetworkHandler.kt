package com.luka.themoviedb.retrofit

sealed class NetworkHandler<T>(val data:T? = null, val errorMessage:String? = null){
    class Success<T>(data: T):NetworkHandler<T>(data)
    class Error<T>(errorMessage: String,data: T? = null):NetworkHandler<T>(data,errorMessage)
}