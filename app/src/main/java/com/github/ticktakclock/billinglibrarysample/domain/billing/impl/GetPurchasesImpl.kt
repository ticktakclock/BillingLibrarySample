package com.github.ticktakclock.billinglibrarysample.domain.billing.impl

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.github.ticktakclock.billinglibrarysample.domain.billing.BillingClientWrapper
import com.github.ticktakclock.billinglibrarysample.domain.billing.usecase.GetPurchases
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class GetPurchasesImpl(private val wrapper: BillingClientWrapper) : GetPurchases {

    override fun execute(): Flow<Purchase> = flow {
        val result = wrapper.billingClient.queryPurchases(BillingClient.SkuType.INAPP)
        Timber.d("fetch purchase ${result.responseCode}")
        result.purchasesList?.forEach {
            emit(it)
        }
    }
}