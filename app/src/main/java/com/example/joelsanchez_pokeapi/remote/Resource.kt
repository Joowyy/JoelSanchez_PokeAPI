package com.example.joelsanchez_pokeapi.remote

class Resource<T> private constructor(
    val status: Status,
    val data: T?,
    val message: String?
) {
    enum class Status { SUCCESS, ERROR, LOADING }

    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)
        fun <T> error(msg: String?): Resource<T> = Resource(Status.ERROR, null, msg)
        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null, null)
    }
}
