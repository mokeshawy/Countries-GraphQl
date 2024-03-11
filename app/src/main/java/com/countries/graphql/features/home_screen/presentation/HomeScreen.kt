package com.countries.graphql.features.home_screen.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.countries.graphql.core.state.ui_data_state.UiDataState
import com.countries.graphql.core.uitls.error_view.ErrorView
import com.countries.graphql.core.uitls.handleOpenConnectionSetting
import com.countries.graphql.core.uitls.loading_view.LoadingView
import com.countries.graphql.features.composable.InternetConnectionView
import com.countries.graphql.features.home_screen.domain.model.SimpleCountry
import com.countries.graphql.features.home_screen.domain.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    isNetworkAvailable: Boolean,
    viewModel: HomeViewModel = koinViewModel(),
    onItemClicked: (String) -> Unit
) {

    val context = LocalContext.current
    val uiState by viewModel.countriesResponseState.collectAsState()

    InternetConnectionView(isNetworkAvailable, composable = {
        HomeScreen(uiDataState = uiState, viewModel = viewModel, onItemClicked = onItemClicked)
    }, action = {
        context.handleOpenConnectionSetting()
    })
}

@Composable
private fun HomeScreen(
    uiDataState: UiDataState<List<SimpleCountry>>,
    viewModel: HomeViewModel,
    onItemClicked: (String) -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (uiDataState) {
            is UiDataState.Loading -> LoadingView()
            is UiDataState.Loaded -> {
                val data = viewModel.getCountriesSortedByName()
                Countries(data) { onItemClicked(it) }
            }

            is UiDataState.Error -> ErrorView(uiDataState.error) {
                viewModel.getCountries()
            }
        }
    }
}



