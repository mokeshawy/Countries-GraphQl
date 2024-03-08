package com.countries.graphql.core.network

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.countries.graphql.R
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import zerobranch.androidremotedebugger.logging.NetLoggingInterceptor

class ApolloNetwork(context: Context) {

    private val provideAuthInterceptor: Interceptor =
        Interceptor { chain ->
            val newBuilder = chain.request().newBuilder()
            newBuilder.addHeader("Authorization", "BearerYOUR_ACCESS_TOKEN")
            newBuilder.build().let { chain.proceed(it) }
        }

    private val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
        Timber.tag("OkHttp").d(message)
    }.setLevel(HttpLoggingInterceptor.Level.BODY)


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(provideAuthInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(NetLoggingInterceptor())
        .build()

    val apolloClient = ApolloClient.Builder()
        .serverUrl(context.getString(R.string.base_url))
        .okHttpClient(okHttpClient)
        .build()

}
