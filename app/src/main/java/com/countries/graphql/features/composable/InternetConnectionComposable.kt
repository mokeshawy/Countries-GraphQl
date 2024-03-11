package com.countries.graphql.features.composable

import androidx.compose.runtime.Composable
import com.countries.graphql.R
import com.countries.graphql.core.error_handling.ui_error.error_text.ErrorText
import com.countries.graphql.core.uitls.error_view.ErrorView


@Composable
fun InternetConnectionView(
    isNetworkAvailable: Boolean?,
    composable: @Composable () -> Unit,
    action: () -> Unit
) {
    if (isNetworkAvailable == true) {
        composable()
    } else {
        ErrorView(ErrorText.StringResource(R.string.errorNetworkUnavailable), action)
    }
}