package com.countries.graphql.core.di

import com.countries.graphql.core.connectivity.di.connectivityManagerModule
import org.koin.dsl.module


    val networkModule = module {

    }

    val viewModelsModule = module {

    }

    val repositoriesModule = module {

    }

    val useCaseModule = module {

    }

    val singletonModule = module {
        includes(connectivityManagerModule)
    }