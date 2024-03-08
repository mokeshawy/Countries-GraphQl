package com.countries.graphql.features.home_screen.domain.model

data class Languages(
    val native: String,
    val name: String,
    val code: String,
    val rtl: Boolean
)