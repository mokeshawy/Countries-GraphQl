package com.countries.graphql.core.connectivity.connectivity_manager

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import android.net.NetworkRequest
import androidx.activity.ComponentActivity
import androidx.lifecycle.*
import com.yabraa.medical.core.connectivity.connectivity_manager.NetworkAwareComponent
import com.countries.graphql.core.connectivity.internet_access_observer.InternetAccessObserver
import kotlinx.coroutines.launch

class ConnectivityManager (
    private val activity: Activity,
    private val internetAccessObserver: InternetAccessObserver
) {

    private var _isNetworkConnected = MutableLiveData<Boolean>()
    val isNetworkConnected: LiveData<Boolean> = _isNetworkConnected

    private var networkCapabilities: NetworkCapabilities? = null
    private var getNetworkRequest = getNetworkRequest()
    private var networkCallback = getNetworkCallBack()
    private val componentActivity get() = (activity as ComponentActivity)
    private val networkManagerHolder = (activity as? NetworkAwareComponent)

    init {
        handleNetworkCallbackRegistration()
    }

    private fun handleNetworkCallbackRegistration() {
        componentActivity.lifecycle.addObserver(object : DefaultLifecycleObserver {

            override fun onCreate(owner: LifecycleOwner) {
                super.onCreate(owner)
                getConnectivityManager().registerNetworkCallback(getNetworkRequest, networkCallback)
                handleUnregisteredNetworkState()
                componentActivity.lifecycleScope.launch {
                    observeOnIsInternetAvailable()
                }
            }

            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                getConnectivityManager().unregisterNetworkCallback(networkCallback)
            }
        })
    }


    private fun observeOnIsInternetAvailable() {
        internetAccessObserver.isInternetAvailable.observe(componentActivity) {
            _isNetworkConnected.postValue(it)
        }
    }

    private fun getNetworkRequest(): NetworkRequest {
        return NetworkRequest.Builder()
            .addTransportType(TRANSPORT_WIFI)
            .addTransportType(TRANSPORT_CELLULAR)
            .addTransportType(TRANSPORT_ETHERNET)
            .build()
    }


    private fun getNetworkCallBack(): ConnectivityManager.NetworkCallback {
        return object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                networkManagerHolder?.onNetworkAvailable(network)
                networkCapabilities = getConnectivityManager().getNetworkCapabilities(network)
                checkConnectInternetType()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                networkManagerHolder?.onNetworkLost(network)
                getInternetAccessResponse()
            }
        }
    }

    private fun handleUnregisteredNetworkState() {
        if (getActiveNetwork() == null)
            getInternetAccessResponse()
    }

    private fun getActiveNetwork() =
        getConnectivityManager().getNetworkCapabilities(getConnectivityManager().activeNetwork)


    private fun getConnectivityManager() =
        activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    private fun checkConnectInternetType() {
        networkCapabilities?.let {
            when {
                it.hasTransport(TRANSPORT_CELLULAR) -> getInternetAccessResponse()
                it.hasTransport(TRANSPORT_WIFI) -> getInternetAccessResponse()
                it.hasTransport(TRANSPORT_ETHERNET) -> getInternetAccessResponse()
            }
        }
    }


    private fun getInternetAccessResponse() = internetAccessObserver.getInternetAccessResponse()
}