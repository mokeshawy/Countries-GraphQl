package com.countries.graphql.core.error_handling.error_type_converter

import com.countries.graphql.core.error_handling.error_type.ErrorType
import com.countries.graphql.core.error_handling.ui_error.error_text.ErrorText

interface ErrorTypeConverterHandler {
    fun convert(errorType: ErrorType): ErrorText
}