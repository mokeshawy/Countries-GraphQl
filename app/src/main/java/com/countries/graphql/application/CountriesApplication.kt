package com.countries.graphql.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class CountriesApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin{
            androidLogger()
            androidContext(this@CountriesApplication)
        }
    }
}