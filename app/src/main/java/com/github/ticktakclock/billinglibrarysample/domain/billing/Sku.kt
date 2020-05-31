package com.github.ticktakclock.billinglibrarysample.domain.billing

import com.android.billingclient.api.SkuDetails

sealed class Sku {
    data class Available(val skuDetails: SkuDetails) : Sku()
    data class AlreadyOwned(val skuDetails: SkuDetails) : Sku()
    object None : Sku()
}