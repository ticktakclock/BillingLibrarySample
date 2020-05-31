package com.github.ticktakclock.billinglibrarysample.ui.purchase

import androidx.lifecycle.*
import com.android.billingclient.api.Purchase
import com.github.ticktakclock.billinglibrarysample.domain.billing.usecase.ConsumePurchase
import com.github.ticktakclock.billinglibrarysample.domain.billing.usecase.GetPurchases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import timber.log.Timber

class PurchaseViewModel(
    private val getPurchases: GetPurchases,
    private val consumePurchase: ConsumePurchase
) : ViewModel(), LifecycleObserver {

    private val _purchases = MutableLiveData<List<Purchase>>()
    val purchases: LiveData<List<Purchase>> = _purchases

    private val _consume = MutableLiveData<Purchase>()
    val consume: LiveData<Purchase> = _consume

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        fetchPurchases()
    }

    private fun fetchPurchases() {
        viewModelScope.launch(Dispatchers.IO) {
            val purchases = getPurchases.execute().toList()
            _purchases.postValue(purchases)
        }
    }

    fun onClickPurchase(purchase: Purchase) {
        if (Purchase.PurchaseState.PURCHASED == purchase.purchaseState) {
            Timber.d("onClick purchased.")
            if (purchase.isAcknowledged.not()) {
                _consume.postValue(purchase)
            } else {
                Timber.d("purchase already acknowledged.")
            }
        } else {
            Timber.d("onClick purchase state is ${purchase.purchaseState} ")
        }
    }

    fun onApplyConsume(purchase: Purchase) {
        viewModelScope.launch(Dispatchers.IO) {
            if (purchase.isAcknowledged.not()) {
                consumePurchase.execute(purchase).collect {
                    Timber.d("${it.billingResult.responseCode}")
                    fetchPurchases()
                }
            }
        }
    }
}