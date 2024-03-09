package com.countries.graphql.features.country_details_screen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.countries.graphql.features.home_screen.domain.model.DetailedCountry

@Composable
fun DetailedCountry(country: DetailedCountry) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = country.emoji,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = country.name,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Continent: " + country.continent)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Currency: " + country.currency)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Capital: " + country.capital)
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(8.dp))
    }
}