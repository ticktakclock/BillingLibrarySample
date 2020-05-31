package com.github.ticktakclock.billinglibrarysample.domain.billing.impl

import android.app.Activity
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.SkuDetails
import com.github.ticktakclock.billinglibrarysample.domain.billing.BillingClientWrapper
import com.github.ticktakclock.billinglibrarysample.domain.billing.Sku
import com.github.ticktakclock.billinglibrarysample.domain.billing.usecase.StartBilling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StartBillingImpl(private val wrapper: BillingClientWrapper) : StartBilling {
    override fun execute(activity: Activity, skuDetails: SkuDetails): Flow<Sku> = flow {
        val flowParams = BillingFlowParams.newBuilder()
            .setSkuDetails(skuDetails)
            .build()

        val billingResult = wrapper.billingClient.launchBillingFlow(activity, flowParams)
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                emit(Sku.Available(skuDetails))
            }
            BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                emit(Sku.AlreadyOwned(skuDetails))
            }
        }

    }
}