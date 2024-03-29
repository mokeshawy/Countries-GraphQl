package com.countries.graphql.features.country_details_screen.domain.repository

import com.countries.graphql.core.state.network_state.State
import com.countries.graphql.features.home_screen.domain.model.DetailedCountry
import kotlinx.coroutines.flow.Flow

interface DetailedCountryRepository {
    suspend fun getCountry(code: String): Flow<State<DetailedCountry>>
}