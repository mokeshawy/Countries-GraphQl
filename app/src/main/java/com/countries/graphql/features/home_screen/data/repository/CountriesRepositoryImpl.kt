package com.countries.graphql.features.home_screen.data.repository

import com.countries.graphql.core.error_handling.throwable.toErrorType
import com.countries.graphql.core.state.network_state.State
import com.countries.graphql.features.home_screen.domain.data_source.CountriesDataSource
import com.countries.graphql.features.home_screen.domain.repository.CountriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

class CountriesRepositoryImpl(private val countriesDataSource: CountriesDataSource) :
    CountriesRepository {
    override suspend fun getCountries() =
        countriesDataSource.fetchCountries().catch {
            emit(State.Error(it.toErrorType()))
        }.flowOn(Dispatchers.IO)
}