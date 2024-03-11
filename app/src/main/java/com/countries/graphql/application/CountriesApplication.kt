package com.countries.graphql.application

import android.app.Application
import com.countries.graphql.BuildConfig
import com.countries.graphql.core.di.appModule
import com.countries.graphql.core.di.dataSourceModule
import com.countries.graphql.core.di.networkModule
import com.countries.graphql.core.di.repositoriesModule
import com.countries.graphql.core.di.singletonModule
import com.countries.graphql.core.di.useCaseModule
import com.countries.graphql.core.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import timber.log.Timber
import zerobranch.androidremotedebugger.AndroidRemoteDebugger

class CountriesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        plantTimberTrees()
        initRemoteDebugger()
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@CountriesApplication)
            modules(
                appModule,
                networkModule,
                viewModelsModule,
                dataSourceModule,
                repositoriesModule,
                useCaseModule,
                singletonModule
            )
        }
    }


    private fun plantTimberTrees() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.plant(RemoteDebuggerTree())
        }
    }

    private fun initRemoteDebugger() {
        if (!BuildConfig.DEBUG) return
        val remoteDebugger =
            AndroidRemoteDebugger.Builder(applicationContext).disableInternalLogging()
                .port(getRemoteDebuggerPort()).build()
        AndroidRemoteDebugger.init(remoteDebugger)
    }

    private fun getRemoteDebuggerPort() = 9096

    private inner class RemoteDebuggerTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            AndroidRemoteDebugger.Log.log(priority, tag, message, t)
        }
    }
}