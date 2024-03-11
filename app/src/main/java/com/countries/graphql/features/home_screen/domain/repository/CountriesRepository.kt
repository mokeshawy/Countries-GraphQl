package com.countries.graphql.features.home_screen.domain.repository

import com.countries.graphql.core.state.network_state.State
import com.countries.graphql.features.home_screen.domain.model.SimpleCountry
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    suspend fun getCountries() : Flow<State<List<SimpleCountry>>>
}