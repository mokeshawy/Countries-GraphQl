package com.countries.graphql.features.home_screen.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countries.graphql.core.error_handling.error_type.ErrorType
import com.countries.graphql.core.error_handling.error_type_converter.ErrorTypeConverterHandler
import com.countries.graphql.core.state.network_state.State
import com.countries.graphql.core.state.ui_data_state.UiDataState
import com.countries.graphql.features.home_screen.domain.model.SimpleCountry
import com.countries.graphql.features.home_screen.domain.usecase.CountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val countriesUseCase: CountriesUseCase by inject()
    private val errorTypeConverterHandler: ErrorTypeConverterHandler by inject()

    private val _countriesResponseState =
        MutableStateFlow<UiDataState<List<SimpleCountry>>>(UiDataState.Loading())
    val countriesResponseState = _countriesResponseState.asStateFlow()

    init {
        getCountries()
    }

    fun getCountries() {
        viewModelScope.launch {
            countriesUseCase().collect {
                when (it) {
                    is State.Success -> it.data?.handleCountriesSuccessState()
                    is State.Error -> it.error.handleCountriesErrorState()
                }
            }
        }
    }

    private suspend fun List<SimpleCountry>.handleCountriesSuccessState() =
        _countriesResponseState.emit(UiDataState.Loaded(this))


    private suspend fun ErrorType.handleCountriesErrorState() = _countriesResponseState.emit(
        UiDataState.Error(errorTypeConverterHandler.convert(this))
    )


    fun getCountriesSortedByName() = countriesUseCase.getCountriesSortedByName()
}
