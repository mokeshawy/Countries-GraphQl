package com.countries.graphql.features.country_details_screen.presentation

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
import com.countries.graphql.features.country_details_screen.domain.viewmodel.DetailedCountryViewModel
import com.countries.graphql.features.home_screen.domain.model.DetailedCountry
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailedCountryScreen(
    isNetworkAvailable: Boolean?,
    viewModel: DetailedCountryViewModel = koinViewModel()
) {

    val context = LocalContext.current
    val state by viewModel.detailedCountryResponseState.collectAsState()

    InternetConnectionView(isNetworkAvailable == true, composable = {
        DetailedCountryScreen(state, viewModel)
    }, action = {
        context.handleOpenConnectionSetting()
    })
}

@Composable
fun DetailedCountryScreen(
    uiDataState: UiDataState<DetailedCountry>,
    viewModel: DetailedCountryViewModel
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (uiDataState) {
            is UiDataState.Error -> ErrorView(uiDataState.error) {
                viewModel.getDetailedCountry()
            }

            is UiDataState.Loading -> LoadingView()
            is UiDataState.Loaded -> {
                val data = viewModel.getCountry()
                data?.let { DetailedCountry(it) }
            }
        }
    }
}