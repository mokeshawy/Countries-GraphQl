package com.countries.graphql.core.uitls

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings

fun Context.handleOpenConnectionSetting() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        startActivity(Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY))
    } else {
        startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
    }
