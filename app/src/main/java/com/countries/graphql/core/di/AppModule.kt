package com.countries.graphql.core.di

import com.countries.graphql.core.connectivity.di.connectivityManagerModule
import com.countries.graphql.core.network.ApolloNetwork
import com.countries.graphql.features.home_screen.di.homeRepositoryModule
import com.countries.graphql.features.home_screen.di.homeUseCaseModule
import com.countries.graphql.features.home_screen.di.homeViewModelModule
import org.koin.dsl.module


val networkModule = module {
    single { ApolloNetwork(get()) }
}

val viewModelsModule = module {
    includes(homeViewModelModule)
}

val repositoriesModule = module {
    includes(homeRepositoryModule)
}

val useCaseModule = module {
    includes(homeUseCaseModule)
}

val singletonModule = module {
    includes(connectivityManagerModule)
}