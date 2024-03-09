package com.countries.graphql.features.home_screen.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.countries.graphql.features.home_screen.domain.model.SimpleCountry


@Composable
fun Countries(countries: List<SimpleCountry>, onItemClicked: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(countries) { country ->
            CountryItem(
                country = country, modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClicked(country.code) }
                    .padding(16.dp)
            )
        }
    }
}

@Composable()
private fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {

        Text(text = country.emoji, fontSize = 30.sp)

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {

            Text(text = country.name, fontSize = 24.sp)

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = country.capital ?: "")
        }
    }
}