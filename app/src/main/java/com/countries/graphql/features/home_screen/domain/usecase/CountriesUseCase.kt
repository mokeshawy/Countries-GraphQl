package com.countries.graphql.features.home_screen.domain.usecase

import com.countries.graphql.core.state.State
import com.countries.graphql.features.home_screen.domain.model.SimpleCountry
import com.countries.graphql.features.home_screen.domain.repository.countries_repository.CountriesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.channelFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class CountriesUseCase : KoinComponent {

    private val countriesRepository: CountriesRepository by inject()

    private var simpleCountry: List<SimpleCountry>? = null

    suspend operator fun invoke() = channelFlow {
        val response = async { countriesRepository.getCountries() }
        response.await().collect {
            if (it is State.Success) {
                simpleCountry = it.data
            }
            send(it)
        }
    }

    fun getCountriesSortedByName() =
        simpleCountry?.sortedBy { it.name }?.filter { it.name != "Israel" } ?: emptyList()
}