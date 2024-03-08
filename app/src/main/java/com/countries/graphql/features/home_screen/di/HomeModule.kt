package com.countries.graphql.features.home_screen.di

import com.countries.graphql.features.home_screen.data.repository.countries_repository.CountriesRepositoryImpl
import com.countries.graphql.features.home_screen.data.repository.detailed_country_repository.DetailedCountryRepositoryImpl
import com.countries.graphql.features.home_screen.domain.repository.countries_repository.CountriesRepository
import com.countries.graphql.features.home_screen.domain.repository.detailed_country_repository.DetailedCountryRepository
import com.countries.graphql.features.home_screen.domain.usecase.CountriesUseCase
import com.countries.graphql.features.home_screen.domain.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val homeViewModelModule = module {
    viewModel { HomeViewModel() }
}

val homeRepositoryModule = module {
    factory<CountriesRepository> { CountriesRepositoryImpl(get()) }
    factory<DetailedCountryRepository> { DetailedCountryRepositoryImpl(get()) }
}

val homeUseCaseModule = module {
    factory { CountriesUseCase() }
}