package com.countries.graphql.core.state.network_state

import com.countries.graphql.core.error_handling.error_type.ErrorType


sealed class State<T> {
    data class Success<T>(val data: T? = null, val error: T? = null) : State<T>()
    data class Error<T>(val error: ErrorType) : State<T>()
}