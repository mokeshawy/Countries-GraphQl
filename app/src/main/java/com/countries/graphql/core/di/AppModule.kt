package com.countries.graphql.core.di

import com.countries.graphql.core.connectivity.di.connectivityManagerModule
import com.countries.graphql.core.network.ApolloNetwork
import com.countries.graphql.features.country_details_screen.di.detailedCountryRepositoryModule
import com.countries.graphql.features.country_details_screen.di.detailedCountryUseCaseModule
import com.countries.graphql.features.country_details_screen.di.detailedCountryViewModelModule
import com.countries.graphql.features.home_screen.di.homeRepositoryModule
import com.countries.graphql.features.home_screen.di.homeUseCaseModule
import com.countries.graphql.features.home_screen.di.homeViewModelModule
import org.koin.dsl.module


val networkModule = module {
    single { ApolloNetwork(get()) }
}

val viewModelsModule = module {
    includes(homeViewModelModule)
    includes(detailedCountryViewModelModule)
}

val repositoriesModule = module {
    includes(homeRepositoryModule)
    includes(detailedCountryRepositoryModule)
}

val useCaseModule = module {
    includes(homeUseCaseModule)
    includes(detailedCountryUseCaseModule)
}

val singletonModule = module {
    includes(connectivityManagerModule)
}