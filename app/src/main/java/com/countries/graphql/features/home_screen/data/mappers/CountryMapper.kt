package com.countries.graphql.features.home_screen.data.mappers

import com.countries.graphql.CountryQuery
import com.countries.graphql.features.home_screen.domain.model.DetailedCountry
import com.countries.graphql.features.home_screen.domain.model.Languages


fun CountryQuery.Country.toDetailedCountry() = DetailedCountry(
    name = name,
    capital = capital ?: "No Capital",
    code = code,
    currency = currency ?: "No Currency",
    emoji = emoji,
    native = native,
    phone = phone,
    languages = languages.map { it.toLanguage() },
    continent = continent.name
)


fun CountryQuery.Language.toLanguage() = Languages(
    native = native,
    name = name,
    code = code,
    rtl = rtl
)