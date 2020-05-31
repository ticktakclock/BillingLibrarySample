package com.github.ticktakclock.billinglibrarysample.ui.purchase

import androidx.lifecycle.Observer
import com.airbnb.epoxy.TypedEpoxyController
import com.android.billingclient.api.Purchase
import com.github.ticktakclock.billinglibrarysample.purchase

class PurchaseController(private val onClickPurchase: (Purchase) -> Unit) :
    TypedEpoxyController<List<Purchase>>() {

    val purchaseObserver = Observer<List<Purchase>> {
        setData(it)
    }

    override fun buildModels(data: List<Purchase>?) {
        data ?: return
        data.forEach {
            purchase {
                id(modelCountBuiltSoFar)
                purchase(it)
                onClickPurchase { _ ->
                    onClickPurchase(it)
                }
            }
        }
    }
}