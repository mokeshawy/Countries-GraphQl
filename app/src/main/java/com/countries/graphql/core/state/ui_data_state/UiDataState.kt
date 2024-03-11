package com.countries.graphql.core.state.ui_data_state

import com.countries.graphql.core.error_handling.ui_error.error_text.ErrorText

sealed class UiDataState<T> {
    class Loading<T>: UiDataState<T>()
    data class Error<T>(val error: ErrorText) : UiDataState<T>()
    data class Loaded<T>(val data: T): UiDataState<T>()
}