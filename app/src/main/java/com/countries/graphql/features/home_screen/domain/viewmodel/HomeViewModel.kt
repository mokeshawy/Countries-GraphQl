package com.countries.graphql.features.home_screen.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countries.graphql.core.state.State
import com.countries.graphql.features.home_screen.domain.model.SimpleCountry
import com.countries.graphql.features.home_screen.domain.usecase.CountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val countriesUseCase: CountriesUseCase by inject()

    private val _countriesResponseState =
        MutableStateFlow<State<List<SimpleCountry>>>(State.Initial())
    val countriesResponseState = _countriesResponseState.asStateFlow()

    init {
        getCountries()
    }

    private fun getCountries() {
        viewModelScope.launch {
            _countriesResponseState.emit(State.Loading())
            countriesUseCase().collect {
                _countriesResponseState.emit(it)
            }
        }
    }

    fun getCountriesSortedByName() = countriesUseCase.getCountriesSortedByName()
}