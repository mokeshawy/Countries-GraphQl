package com.countries.graphql.core.error_handling.ui_error.error_text

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class ErrorText {
    class StringResource(@StringRes val id: Int): ErrorText()

    @Composable
    fun asString() = when(this) {
        is StringResource -> stringResource(id)
    }
}