package com.countries.graphql.features.country_details_screen.data.repository

import com.countries.graphql.core.error_handling.throwable.toErrorType
import com.countries.graphql.core.state.network_state.State
import com.countries.graphql.features.country_details_screen.domain.data_source.DetailedCountryDataSource
import com.countries.graphql.features.country_details_screen.domain.repository.DetailedCountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

class DetailedCountryRepositoryImpl(private val detailedCountryDataSource: DetailedCountryDataSource) :
    DetailedCountryRepository {
    override suspend fun getCountry(code: String) =
        detailedCountryDataSource.fetchCountry(code).catch {
            emit(State.Error(it.toErrorType()))
        }.flowOn(Dispatchers.IO)
}