package com.joenjogu.nexflix.data.core

data class Result<out T>(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS,
        LOADING,
        ERROR
    }

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(Status.SUCCESS, data, null )
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, null)
        }

        fun <T> error(message: String?, data: T? = null): Result<T> {
            return Result(Status.ERROR, data, message)
        }
    }
}
