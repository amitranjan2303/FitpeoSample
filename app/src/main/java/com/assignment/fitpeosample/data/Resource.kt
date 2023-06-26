package com.assignment.fitpeosample.data

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
){
    class Loading<T>(): Resource<T>()
    class Success<T>(data: T): Resource<T>(data)
    class Failure<T>(data: T? = null, message: String): Resource<T>(data,message)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[exception=$message]"
            is Loading<T> -> "Loading"
        }
    }
}