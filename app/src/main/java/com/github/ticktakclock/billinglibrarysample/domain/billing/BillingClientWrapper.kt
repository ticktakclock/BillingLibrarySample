package com.github.ticktakclock.billinglibrarysample.domain.billing

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import timber.log.Timber

class BillingClientWrapper(billingBuilder: BillingClient.Builder) : PurchasesUpdatedListener {

    // setListener(this).enablePendingPurchases()の2つを呼ばないとIllegalStateExceptionが発生する
    val billingClient = billingBuilder.setListener(this).enablePendingPurchases().build()

    override fun onPurchasesUpdated(p0: BillingResult?, p1: MutableList<Purchase>?) {
        Timber.d(p0?.debugMessage)
        Timber.d("purchase list :%s", p1?.size)
    }
}