package com.github.ticktakclock.billinglibrarysample.ui

import android.app.Activity
import androidx.lifecycle.*
import com.github.ticktakclock.billinglibrarysample.domain.billing.ConnectionState
import com.github.ticktakclock.billinglibrarysample.domain.billing.Sku
import com.github.ticktakclock.billinglibrarysample.domain.billing.usecase.*
import com.github.ticktakclock.billinglibrarysample.domain.coin.Coin
import com.github.ticktakclock.billinglibrarysample.domain.coin.usecase.GetCoins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val getCoins: GetCoins,
    private val startConnection: StartConnection,
    private val getSku: GetSku,
    private val startBilling: StartBilling,
    private val getPurchases: GetPurchases,
    private val consumePurchase: ConsumePurchase
) : ViewModel(), LifecycleObserver {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> = _coins

    private val _sku = MutableLiveData<Sku.Available>()
    val sku: LiveData<Sku.Available> = _sku

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Timber.d("MainViewModel::onCreate")
        fetchCoins()
    }

    private fun fetchCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            getCoins.execute()
                .collect {
                    Timber.d("data collect")
                    _coins.postValue(it)
                }
        }
    }

    fun onClickCoin(coin: Coin) {
        Timber.d("coin clicked $coin")
        viewModelScope.launch(Dispatchers.IO) {
            when (startConnection.execute()) {
                ConnectionState.Connected -> {
                    Timber.d("client connected")
                    querySkuFlow(coin.sku)
                }
                ConnectionState.Disconnected -> {
                    Timber.d("client disconnected")
                }
                ConnectionState.Error -> {
                    Timber.d("client error")
                }
            }
        }
    }

    private suspend fun querySkuFlow(sku: String) {
        getSku.execute(sku)
            .collect {
                when (it) {
                    is Sku.Available -> {
                        Timber.d("${it.skuDetails}")
                        _sku.postValue(it)
                    }
                    Sku.None -> {
                        Timber.d("no available sku.")
                    }
                }
            }
    }

    fun startBilling(activity: Activity, sku: Sku.Available) {
        viewModelScope.launch(Dispatchers.IO) {
            startBilling.execute(activity, sku.skuDetails).collect {
                when (it) {
                    is Sku.Available -> {
                        Timber.d("Billing Flow succeeded.")
                        consume()
                    }
                    is Sku.AlreadyOwned -> {
                        Timber.d("Already Owned.")
                        consume()
                    }
                }
            }
        }
    }

    suspend fun consume() {
        getPurchases.execute().collect {
            consumePurchase.execute(it).collect {
                Timber.d("${it.billingResult.responseCode}")
            }
        }
    }
}