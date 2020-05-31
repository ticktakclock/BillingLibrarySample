package com.github.ticktakclock.billinglibrarysample.domain.billing.usecase

import com.android.billingclient.api.ConsumeResult
import com.android.billingclient.api.Purchase
import kotlinx.coroutines.flow.Flow

interface ConsumePurchase {
    fun execute(purchase: Purchase): Flow<ConsumeResult>
}