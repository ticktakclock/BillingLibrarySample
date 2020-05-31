package com.github.ticktakclock.billinglibrarysample.domain.billing

sealed class ConnectionState {

    object Connected : ConnectionState()
    object Disconnected : ConnectionState()
    object Error : ConnectionState()
}