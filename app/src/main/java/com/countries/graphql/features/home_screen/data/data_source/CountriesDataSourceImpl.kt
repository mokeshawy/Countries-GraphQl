package com.countries.graphql.features.home_screen.data.data_source

import com.apollographql.apollo3.ApolloClient
import com.countries.graphql.CountriesQuery
import com.countries.graphql.core.state.network_state.State
import com.countries.graphql.features.home_screen.data.mappers.toSimpleCountry
import com.countries.graphql.features.home_screen.domain.data_source.CountriesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CountriesDataSourceImpl(private val apolloNetwork: ApolloClient) : CountriesDataSource {
    override suspend fun fetchCountries() = flow {
        val response = apolloNetwork.query(CountriesQuery()).execute()
        val data = response.data?.countries?.map { it.toSimpleCountry() } ?: emptyList()
        if (response.errors.isNullOrEmpty()) emit(State.Success(data))
    }.flowOn(Dispatchers.IO)
}