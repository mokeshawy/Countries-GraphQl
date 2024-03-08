package com.countries.graphql.features.home_screen.data.repository.detailed_country_repository

import com.countries.graphql.CountryQuery
import com.countries.graphql.core.network.ApolloNetwork
import com.countries.graphql.core.state.State
import com.countries.graphql.features.home_screen.data.mappers.toDetailedCountry
import com.countries.graphql.features.home_screen.domain.repository.detailed_country_repository.DetailedCountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DetailedCountryRepositoryImpl(private val apolloNetwork: ApolloNetwork) :
    DetailedCountryRepository {
    override suspend fun getCountry(code: String) = flow {
        val country = apolloNetwork.apolloClient.query(CountryQuery(code))
            .execute().data?.country?.toDetailedCountry()
        emit(State.Success(country))
    }.catch {
        State.Error(it.message,it.cause)
    }.flowOn(Dispatchers.IO)
}