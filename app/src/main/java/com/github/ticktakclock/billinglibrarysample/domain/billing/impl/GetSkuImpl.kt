package com.github.ticktakclock.billinglibrarysample.domain.billing.impl

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.SkuDetailsParams
import com.android.billingclient.api.querySkuDetails
import com.github.ticktakclock.billinglibrarysample.domain.billing.BillingClientWrapper
import com.github.ticktakclock.billinglibrarysample.domain.billing.Sku
import com.github.ticktakclock.billinglibrarysample.domain.billing.usecase.GetSku
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetSkuImpl(
    private val wrapper: BillingClientWrapper
) : GetSku {
    override fun execute(sku: String): Flow<Sku> = flow {
        val skuList = ArrayList<String>()
        skuList.add(sku)
        val params = SkuDetailsParams.newBuilder()
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP)
        val skuDetailsResult = withContext(Dispatchers.IO) {
            wrapper.billingClient.querySkuDetails(params.build())
        }
        val value = skuDetailsResult.skuDetailsList?.firstOrNull()
        if (value != null) {
            emit(Sku.Available(value))
        } else {
            emit(Sku.None)
        }
    }
}