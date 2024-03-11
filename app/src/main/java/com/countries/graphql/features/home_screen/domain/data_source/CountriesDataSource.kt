package com.countries.graphql.features.home_screen.domain.data_source

import com.countries.graphql.core.state.network_state.State
import com.countries.graphql.features.home_screen.domain.model.SimpleCountry
import kotlinx.coroutines.flow.Flow

interface CountriesDataSource {
    suspend fun fetchCountries() : Flow<State<List<SimpleCountry>>>

}