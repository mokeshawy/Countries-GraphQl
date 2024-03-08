package com.countries.graphql.features.home_screen.domain.usecase

import com.countries.graphql.core.state.State
import com.countries.graphql.features.home_screen.domain.model.DetailedCountry
import com.countries.graphql.features.home_screen.domain.repository.detailed_country_repository.DetailedCountryRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.channelFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailedCountryUseCase : KoinComponent {

    private val detailedCountryRepository: DetailedCountryRepository by inject()

    private var detailedCountry: DetailedCountry? = null

    suspend operator fun invoke(code: String) = channelFlow {
        val response = async { detailedCountryRepository.getCountry(code) }
        response.await().collect {
            if (it is State.Success) {
                detailedCountry = it.data
            }
            send(it)
        }
    }

    fun getDetailedCountry() = detailedCountry
}