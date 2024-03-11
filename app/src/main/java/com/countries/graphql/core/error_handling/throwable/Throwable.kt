package com.countries.graphql.core.error_handling.throwable

import com.apollographql.apollo3.exception.ApolloHttpException
import com.countries.graphql.core.error_handling.error_type.ErrorType
import java.io.IOException


const val INTERNAL_SERVER = 501
const val SERVER_UNAVAILABLE = 503
const val RESOURCE_NOT_FOUND = 404
fun Throwable.toErrorType() = when (this) {
    is IOException -> ErrorType.Network
    is ApolloHttpException -> when (statusCode) {
        RESOURCE_NOT_FOUND -> ErrorType.NotFound
        INTERNAL_SERVER -> ErrorType.Server
        SERVER_UNAVAILABLE -> ErrorType.ServiceUnavailable
        else -> ErrorType.Unknown
    }

    else -> ErrorType.Unknown
}
