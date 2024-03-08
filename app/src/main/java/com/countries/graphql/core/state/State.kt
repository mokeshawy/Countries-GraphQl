package com.countries.graphql.core.state


sealed class State<T> {

    class Initial<T> : State<T>()
    class Loading<T> : State<T>()
    data class Success<T>(val data: T? = null, val error: T? = null) : State<T>()
    data class Error<T>(val error: T, val throwable: Throwable? = null) : State<T>()
}