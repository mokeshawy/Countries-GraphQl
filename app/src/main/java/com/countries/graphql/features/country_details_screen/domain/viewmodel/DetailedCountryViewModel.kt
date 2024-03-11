package com.countries.graphql.features.country_details_screen.domain.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countries.graphql.core.error_handling.error_type.ErrorType
import com.countries.graphql.core.error_handling.error_type_converter.ErrorTypeConverterHandler
import com.countries.graphql.core.state.network_state.State
import com.countries.graphql.core.state.ui_data_state.UiDataState
import com.countries.graphql.features.country_details_screen.domain.usecase.DetailedCountryUseCase
import com.countries.graphql.features.home_screen.domain.model.DetailedCountry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailedCountryViewModel(
    private val stateHandle: SavedStateHandle
) : ViewModel(), KoinComponent {

    private val detailedCountryUseCase: DetailedCountryUseCase by inject()
    private val errorTypeConverterHandler: ErrorTypeConverterHandler by inject()

    private val _detailedCountryResponseState =
        MutableStateFlow<UiDataState<DetailedCountry>>(UiDataState.Loading())

    val detailedCountryResponseState = _detailedCountryResponseState.asStateFlow()

    init {
        getDetailedCountry()
    }

    fun getDetailedCountry() {
        val code = stateHandle.get<String>("code") ?: ""
        getDetailedCountry(code)
    }

    private fun getDetailedCountry(code: String) = viewModelScope.launch {
        detailedCountryUseCase(code).collect {
            when (it) {
                is State.Error -> it.error.handleDetailedCountryErrorState()
                is State.Success -> it.data?.handleDetailedCountrySuccessState()
            }
        }
    }

    private suspend fun DetailedCountry.handleDetailedCountrySuccessState() {
        _detailedCountryResponseState.emit(UiDataState.Loaded(this))
    }

    private suspend fun ErrorType.handleDetailedCountryErrorState() {
        _detailedCountryResponseState.emit(UiDataState.Error(errorTypeConverterHandler.convert(this)))
    }

    fun getCountry() = detailedCountryUseCase.getDetailedCountry()
}