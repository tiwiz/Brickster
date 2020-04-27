package com.rob.legopedia.domain.ui

sealed class LCE<out T>(
    val data: T? = null,
    val error: Throwable? = null
) {

    object Loading : LCE<Nothing>()
    class Complete<T>(data: T) : LCE<T>(data = data)
    class Error(throwable: Throwable) : LCE<Nothing>(error = throwable)
}