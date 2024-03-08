package com.countries.graphql.features.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.countries.graphql.core.connectivity.connectivity_manager.ConnectivityManager
import com.countries.graphql.navigation.AppNavGraph
import com.countries.graphql.ui.theme.CountriesGraphQlTheme
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

    private val connectivityManager: ConnectivityManager by inject { parametersOf(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleNotificationPermission()
        setContent {
            CountriesGraphQlTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isNetworkAvailable =
                        connectivityManager.isNetworkConnected.observeAsState().value
                    AppNavGraph(isNetworkAvailable)
                }
            }
        }
    }

    private fun handleNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionRequest.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            /* no need handle action here */
        }
}
