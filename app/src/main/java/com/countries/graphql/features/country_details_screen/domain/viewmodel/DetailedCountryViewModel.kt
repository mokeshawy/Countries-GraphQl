package com.countries.graphql.features.country_details_screen.domain.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countries.graphql.core.state.State
import com.countries.graphql.features.country_details_screen.domain.usecase.DetailedCountryUseCase
import com.countries.graphql.features.home_screen.domain.model.DetailedCountry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailedCountryViewModel(
    stateHandle: SavedStateHandle
) : ViewModel(), KoinComponent {

    private val detailedCountryUseCase: DetailedCountryUseCase by inject()

    private val _detailedCountryResponseState =
        MutableStateFlow<State<DetailedCountry>>(State.Initial())

    val detailedCountryResponseState = _detailedCountryResponseState.asStateFlow()

    init {
        val code = stateHandle.get<String>("code") ?: ""
        getDetailedCountry(code)
    }

    private fun getDetailedCountry(code: String) = viewModelScope.launch {
        _detailedCountryResponseState.emit(State.Loading())
        detailedCountryUseCase(code).collect {
            _detailedCountryResponseState.emit(it)
        }
    }

    fun getDetailedCountry() = detailedCountryUseCase.getDetailedCountry()
}