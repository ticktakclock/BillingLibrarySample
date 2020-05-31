package com.github.ticktakclock.billinglibrarysample.domain.billing.usecase

import android.app.Activity
import com.android.billingclient.api.SkuDetails
import com.github.ticktakclock.billinglibrarysample.domain.billing.Sku
import kotlinx.coroutines.flow.Flow

interface StartBilling {

    fun execute(activity: Activity, skuDetails: SkuDetails): Flow<Sku>
}