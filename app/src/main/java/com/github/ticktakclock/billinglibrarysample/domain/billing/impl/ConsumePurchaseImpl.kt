package com.github.ticktakclock.billinglibrarysample.domain.billing.impl

import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ConsumeResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.consumePurchase
import com.github.ticktakclock.billinglibrarysample.domain.billing.BillingClientWrapper
import com.github.ticktakclock.billinglibrarysample.domain.billing.usecase.ConsumePurchase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class ConsumePurchaseImpl(private val wrapper: BillingClientWrapper) : ConsumePurchase {
    override fun execute(purchase: Purchase): Flow<ConsumeResult> = flow {
        // 非消費アイテムはacknowledgePurchase()を使用する
//        val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
//            .setPurchaseToken(purchase.purchaseToken)
//            .build()
//        val result = billingClient.acknowledgePurchase(acknowledgePurchaseParams)
        // 消費アイテムはconsumePurchase()を使用する　
        val consumeParam = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        val result = wrapper.billingClient.consumePurchase(consumeParam)
        Timber.d("consume purchase : ${result.billingResult.responseCode}")
        Timber.d("consume purchase token : ${result.purchaseToken}")
        emit(result)
    }
}