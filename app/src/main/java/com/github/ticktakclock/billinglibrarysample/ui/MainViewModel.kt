package com.github.ticktakclock.billinglibrarysample.ui

import androidx.lifecycle.*
import com.github.ticktakclock.billinglibrarysample.domain.Coin
import timber.log.Timber

class MainViewModel : ViewModel(), LifecycleObserver {

    private val _helloText = MutableLiveData<String>()
    val helloText: LiveData<String> = _helloText

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> = _coins

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Timber.d("MainViewModel::onCreate")
        _helloText.postValue("hello onCreate")
        _coins.postValue(listOf(Coin(), Coin(), Coin(), Coin()))
    }

    fun onClickCoin(coin: Coin) {
        Timber.d("coin clicked $coin")
    }
}