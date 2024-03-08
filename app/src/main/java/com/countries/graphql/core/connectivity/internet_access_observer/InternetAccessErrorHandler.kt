package com.countries.graphql.core.connectivity.internet_access_observer

interface InternetAccessErrorHandler {
    fun readInternetAccessExceptionError(errorType: String, exception: Exception) {}
}