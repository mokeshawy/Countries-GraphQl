package com.countries.graphql.features.home_screen.data.mappers

import com.countries.graphql.CountriesQuery
import com.countries.graphql.features.home_screen.domain.model.Languages
import com.countries.graphql.features.home_screen.domain.model.SimpleCountry

fun CountriesQuery.Country.toSimpleCountry() = SimpleCountry(
    name = name,
    capital = capital,
    code = code,
    currency = currency,
    emoji = emoji,
    native = native,
    phone = phone,
    languages = languages.map { it.toLanguage() }

)

fun CountriesQuery.Language.toLanguage() = Languages(
    native = native,
    name = name,
    code = code,
    rtl = rtl
)