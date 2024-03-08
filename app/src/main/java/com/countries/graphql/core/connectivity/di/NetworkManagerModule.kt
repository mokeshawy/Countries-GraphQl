package com.countries.graphql.core.connectivity.di

import android.app.Activity
import com.countries.graphql.core.connectivity.connectivity_manager.ConnectivityManager
import com.countries.graphql.core.connectivity.internet_access_observer.InternetAccessObserver
import org.koin.dsl.module

val connectivityManagerModule = module {
    single { (activity: Activity) ->
        val internetAccessObserver = InternetAccessObserver(activity)
        ConnectivityManager(activity, internetAccessObserver)
    }
}