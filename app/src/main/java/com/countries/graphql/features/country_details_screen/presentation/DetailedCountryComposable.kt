package com.countries.graphql.features.country_details_screen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.countries.graphql.features.home_screen.domain.model.DetailedCountry
import com.countries.graphql.features.home_screen.domain.model.Languages

@Composable
fun DetailedCountry(country: DetailedCountry) {
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(80.dp))
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
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Continent: " + country.continent)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Currency: " + country.currency)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Capital: " + country.capital)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            if (country.languages.isEmpty()) return
            Text(text = "Languages: ", fontSize = 18.sp)
            CountryLanguage(languages = country.languages)
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}

@Composable
private fun CountryLanguage(languages: List<Languages>) {
    LazyColumn(Modifier.fillMaxWidth()) {
        items(languages) { language ->
            LanguageItem(languages = language)
        }
    }
}

@Composable
private fun LanguageItem(languages: Languages) {
    Column(
        Modifier.fillMaxWidth().padding(horizontal = 10.dp),
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Code: " + languages.code)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Native: " + languages.native)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Name: " + languages.name)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Rtl: " + languages.rtl)
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(8.dp))
    }
}