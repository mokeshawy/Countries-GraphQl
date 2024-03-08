package com.countries.graphql.features.home_screen.domain.model

data class DetailedCountry(
    val name: String,
    val capital: String,
    val code: String,
    val currency: String,
    val emoji: String,
    val native: String,
    val phone: String,
    val languages: List<Languages>,
    val continent: String
)