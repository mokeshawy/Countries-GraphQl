package com.countries.graphql.features.country_details_screen.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.countries.graphql.core.state.State
import com.countries.graphql.features.composable.CircularProgress
import com.countries.graphql.features.country_details_screen.domain.viewmodel.DetailedCountryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailedCountryScreen() {

    val viewModel: DetailedCountryViewModel = koinViewModel()
    val state = viewModel.detailedCountryResponseState.collectAsState()
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        when (state.value) {
            is State.Error -> {}
            is State.Initial -> {}
            is State.Loading -> CircularProgress()
            is State.Success -> {
                val data = viewModel.getDetailedCountry()
                data?.let { DetailedCountry(it) }
            }
        }
    }
}