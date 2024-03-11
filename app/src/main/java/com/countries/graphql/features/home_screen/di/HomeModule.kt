package com.countries.graphql.features.home_screen.di

import com.countries.graphql.features.country_details_screen.data.repository.DetailedCountryRepositoryImpl
import com.countries.graphql.features.country_details_screen.domain.repository.DetailedCountryRepository
import com.countries.graphql.features.home_screen.data.data_source.CountriesDataSourceImpl
import com.countries.graphql.features.home_screen.data.repository.CountriesRepositoryImpl
import com.countries.graphql.features.home_screen.domain.data_source.CountriesDataSource
import com.countries.graphql.features.home_screen.domain.repository.CountriesRepository
import com.countries.graphql.features.home_screen.domain.usecase.CountriesUseCase
import com.countries.graphql.features.home_screen.domain.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val homeViewModelModule = module {
    viewModel { HomeViewModel() }
}

val homeDataSourceModule = module {
    single<CountriesDataSource> { CountriesDataSourceImpl(get()) }
}

val homeRepositoryModule = module {
    single<CountriesRepository> { CountriesRepositoryImpl(get()) }
    single<DetailedCountryRepository> { DetailedCountryRepositoryImpl(get()) }
}

val homeUseCaseModule = module {
    single { CountriesUseCase() }
}