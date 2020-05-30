package com.github.ticktakclock.billinglibrarysample.ui

import androidx.lifecycle.*
import timber.log.Timber

class MainViewModel : ViewModel(), LifecycleObserver {

    private val _helloText = MutableLiveData<String>()
    val helloText: LiveData<String> = _helloText

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Timber.d("MainViewModel::onCreate")
        _helloText.postValue("hello onCreate")
    }
}