package com.countries.graphql.features.country_details_screen.di

import com.countries.graphql.features.country_details_screen.data.data_source.DetailedCountryDataSourceImpl
import com.countries.graphql.features.country_details_screen.data.repository.DetailedCountryRepositoryImpl
import com.countries.graphql.features.country_details_screen.domain.data_source.DetailedCountryDataSource
import com.countries.graphql.features.country_details_screen.domain.repository.DetailedCountryRepository
import com.countries.graphql.features.country_details_screen.domain.usecase.DetailedCountryUseCase
import com.countries.graphql.features.country_details_screen.domain.viewmodel.DetailedCountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val detailedCountryViewModelModule = module {
    viewModel { DetailedCountryViewModel(get()) }
}


val detailedCountryDataSourceModule = module {
    single<DetailedCountryDataSource> { DetailedCountryDataSourceImpl(get()) }
}


val detailedCountryRepositoryModule = module {
    single<DetailedCountryRepository> { DetailedCountryRepositoryImpl(get()) }
}

val detailedCountryUseCaseModule = module {
    single { DetailedCountryUseCase() }
}