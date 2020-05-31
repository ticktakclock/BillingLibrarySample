package com.github.ticktakclock.billinglibrarysample.domain.billing.impl

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.github.ticktakclock.billinglibrarysample.domain.billing.BillingClientWrapper
import com.github.ticktakclock.billinglibrarysample.domain.billing.ConnectionState
import com.github.ticktakclock.billinglibrarysample.domain.billing.usecase.StartConnection
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class StartConnectionImpl(private val wrapper: BillingClientWrapper) : StartConnection {

    override suspend fun execute(): ConnectionState = suspendCoroutine { continuation ->
        wrapper.billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                continuation.resume(ConnectionState.Disconnected)
            }

            override fun onBillingSetupFinished(result: BillingResult?) {
                when (result?.responseCode) {
                    BillingClient.BillingResponseCode.OK -> {
                        continuation.resume(ConnectionState.Connected)
                    }
                    else -> {
                        Timber.d("onBillingSetupFinished but not ok. code=${result?.responseCode}")
                        continuation.resume(ConnectionState.Error)
                    }
                }
            }
        })
    }
}