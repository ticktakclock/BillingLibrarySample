package com.github.ticktakclock.billinglibrarysample.ui

import androidx.lifecycle.*
import com.github.ticktakclock.billinglibrarysample.domain.billing.ConnectionState
import com.github.ticktakclock.billinglibrarysample.domain.billing.Sku
import com.github.ticktakclock.billinglibrarysample.domain.billing.usecase.GetSku
import com.github.ticktakclock.billinglibrarysample.domain.billing.usecase.StartConnection
import com.github.ticktakclock.billinglibrarysample.domain.coin.Coin
import com.github.ticktakclock.billinglibrarysample.domain.coin.usecase.GetCoins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val getCoins: GetCoins,
    private val startConnection: StartConnection,
    private val getSku: GetSku
) : ViewModel(), LifecycleObserver {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> = _coins

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
                    getSku.execute(coin.sku)
                        .collect {
                            when (it) {
                                is Sku.Available -> {
                                    Timber.d("${it.skuDetails}")
                                }
                                Sku.None -> {
                                    Timber.d("no available sku.")
                                }
                            }
                        }
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
}