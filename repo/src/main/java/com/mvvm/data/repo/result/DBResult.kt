package com.mvvm.data.repo.result


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class DBResult<out R> {

    open class Success<out T>(val data: T) : DBResult<T>()
    data class Error(val exception: Exception) : DBResult<Nothing>()
    object Loading : DBResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}
