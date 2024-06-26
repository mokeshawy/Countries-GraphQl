package com.countries.graphql.features.country_details_screen.data.data_source

import com.apollographql.apollo3.ApolloClient
import com.countries.graphql.CountryQuery
import com.countries.graphql.core.state.network_state.State
import com.countries.graphql.features.country_details_screen.data.mappers.toDetailedCountry
import com.countries.graphql.features.country_details_screen.domain.data_source.DetailedCountryDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DetailedCountryDataSourceImpl(private val apolloNetwork: ApolloClient) :
    DetailedCountryDataSource {
    override suspend fun fetchCountry(code: String) = flow {
        val response = apolloNetwork.query(CountryQuery(code)).execute()
        val data = response.data?.country?.toDetailedCountry()
        if (response.errors.isNullOrEmpty()) emit(State.Success(data))
    }.flowOn(Dispatchers.IO)
}