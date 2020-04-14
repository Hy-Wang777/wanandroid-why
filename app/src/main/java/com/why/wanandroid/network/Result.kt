package com.why.wanandroid.network

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/14 4:07 PM
 ******************************************/

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}