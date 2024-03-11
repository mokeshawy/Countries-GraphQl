package com.countries.graphql.core.error_handling.error_type

sealed class ErrorType {
    data object Network : ErrorType()
    data object ServiceUnavailable : ErrorType()
    data object NotFound : ErrorType()
    data object Server : ErrorType()
    data object Unknown : ErrorType()
}