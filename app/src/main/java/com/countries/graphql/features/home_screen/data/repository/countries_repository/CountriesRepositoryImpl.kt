package com.countries.graphql.features.home_screen.data.repository.countries_repository

import com.countries.graphql.CountriesQuery
import com.countries.graphql.core.network.ApolloNetwork
import com.countries.graphql.core.state.State
import com.countries.graphql.features.home_screen.data.mappers.toSimpleCountry
import com.countries.graphql.features.home_screen.domain.repository.countries_repository.CountriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CountriesRepositoryImpl(private val apolloNetwork: ApolloNetwork) : CountriesRepository {
    override suspend fun getCountries() = flow {
        val countriesList = apolloNetwork.apolloClient.query(CountriesQuery())
            .execute().data?.countries?.map { it.toSimpleCountry() } ?: emptyList()
        emit(State.Success(countriesList))
    }.catch {
        State.Error(it.message, it.cause)
    }.flowOn(Dispatchers.IO)
}