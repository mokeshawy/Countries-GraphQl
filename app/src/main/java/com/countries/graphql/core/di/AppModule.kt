package com.countries.graphql.core.di

import com.countries.graphql.core.connectivity.di.connectivityManagerModule
import com.countries.graphql.core.error_handling.error_type_converter.di.errorTypeConverterModule
import com.countries.graphql.core.network.provideApolloClient
import com.countries.graphql.features.country_details_screen.di.detailedCountryDataSourceModule
import com.countries.graphql.features.country_details_screen.di.detailedCountryRepositoryModule
import com.countries.graphql.features.country_details_screen.di.detailedCountryUseCaseModule
import com.countries.graphql.features.country_details_screen.di.detailedCountryViewModelModule
import com.countries.graphql.features.home_screen.di.homeDataSourceModule
import com.countries.graphql.features.home_screen.di.homeRepositoryModule
import com.countries.graphql.features.home_screen.di.homeUseCaseModule
import com.countries.graphql.features.home_screen.di.homeViewModelModule
import org.koin.dsl.module


val appModule = module {
    includes(errorTypeConverterModule)
}

val networkModule = module {
    single { provideApolloClient(get()) }
}

val viewModelsModule = module {
    includes(homeViewModelModule)
    includes(detailedCountryViewModelModule)
}

val dataSourceModule = module {
    includes(homeDataSourceModule)
    includes(detailedCountryDataSourceModule)
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