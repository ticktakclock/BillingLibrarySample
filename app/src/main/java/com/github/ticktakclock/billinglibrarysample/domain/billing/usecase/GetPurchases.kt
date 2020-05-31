package com.github.ticktakclock.billinglibrarysample.domain.billing.usecase

import com.android.billingclient.api.Purchase
import kotlinx.coroutines.flow.Flow

interface GetPurchases {
    fun execute(): Flow<Purchase>
}