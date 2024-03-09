package com.countries.graphql.features.home_screen.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.countries.graphql.core.state.State
import com.countries.graphql.features.composable.CircularProgress
import com.countries.graphql.features.home_screen.domain.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    isNetworkAvailable: Boolean?,
    onItemClicked: (String) -> Unit
) {

    val viewModel: HomeViewModel = koinViewModel()
    val state = viewModel.countriesResponseState.collectAsState(initial = State.Initial())

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (state.value) {
            is State.Error -> {}
            is State.Initial -> {}
            is State.Loading -> CircularProgress()
            is State.Success -> {
                val data = viewModel.getCountriesSortedByName()
                Countries(data) { onItemClicked(it) }
            }
        }
    }
}



