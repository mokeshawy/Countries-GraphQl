package com.countries.graphql.core.error_handling.error_type_converter

import com.countries.graphql.R
import com.countries.graphql.core.error_handling.error_type.ErrorType
import com.countries.graphql.core.error_handling.ui_error.error_text.ErrorText

class ErrorTypeConverterImpl : ErrorTypeConverterHandler {
    override fun convert(errorType: ErrorType) = when (errorType) {
        ErrorType.NotFound -> ErrorText.StringResource(R.string.errorResourceNotFound)
        ErrorType.ServiceUnavailable -> ErrorText.StringResource(R.string.errorServiceUnavailable)
        ErrorType.Server -> ErrorText.StringResource(R.string.errorServer)
        ErrorType.Network -> ErrorText.StringResource(R.string.errorNetworkUnavailable)
        ErrorType.Unknown -> ErrorText.StringResource(R.string.errorGeneral)
    }
}