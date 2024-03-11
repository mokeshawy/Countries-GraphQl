package com.countries.graphql.core.error_handling.error_type_converter.di

import com.countries.graphql.core.error_handling.error_type_converter.ErrorTypeConverterHandler
import com.countries.graphql.core.error_handling.error_type_converter.ErrorTypeConverterImpl
import org.koin.dsl.module


val errorTypeConverterModule = module {
    single<ErrorTypeConverterHandler> { ErrorTypeConverterImpl() }
}