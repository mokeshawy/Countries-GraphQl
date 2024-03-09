package com.countries.graphql.features.country_details_screen.di

import com.countries.graphql.features.country_details_screen.data.repository.detailed_country_repository.DetailedCountryRepositoryImpl
import com.countries.graphql.features.country_details_screen.domain.repository.detailed_country_repository.DetailedCountryRepository
import com.countries.graphql.features.country_details_screen.domain.usecase.DetailedCountryUseCase
import com.countries.graphql.features.country_details_screen.domain.viewmodel.DetailedCountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val detailedCountryViewModelModule = module {
    viewModel { DetailedCountryViewModel(get()) }
}

val detailedCountryRepositoryModule = module {
    single<DetailedCountryRepository> { DetailedCountryRepositoryImpl(get()) }
}

val detailedCountryUseCaseModule = module {
    single { DetailedCountryUseCase() }
}