package com.countries.graphql.features.country_details_screen.domain.data_source

import com.countries.graphql.core.state.network_state.State
import com.countries.graphql.features.home_screen.domain.model.DetailedCountry
import kotlinx.coroutines.flow.Flow

interface DetailedCountryDataSource {
    suspend fun fetchCountry(code: String): Flow<State<DetailedCountry>>
}