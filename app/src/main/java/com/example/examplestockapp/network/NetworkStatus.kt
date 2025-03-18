package com.example.examplestockapp.network

import androidx.annotation.StringRes
import com.example.examplestockapp.R

enum class NetworkStatus(@StringRes val textId : Int) {
    Offline(R.string.offline),
    Connecting(R.string.connecting),
    ConnectSuccess(R.string.connecting_success),
    ConnectFailed(R.string.connect_failed),
}