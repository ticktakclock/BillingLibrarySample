package com.github.ticktakclock.billinglibrarysample.ui

import androidx.lifecycle.Observer
import com.airbnb.epoxy.TypedEpoxyController
import com.github.ticktakclock.billinglibrarysample.coin
import com.github.ticktakclock.billinglibrarysample.domain.coin.Coin

class CoinController(private val onClickCoin: (Coin) -> Unit) :
    TypedEpoxyController<List<Coin>>() {

    val coinsObserver = Observer<List<Coin>> {
        setData(it)
    }

    override fun buildModels(data: List<Coin>?) {
        data ?: return
        data.forEach {
            coin {
                id(modelCountBuiltSoFar)
                coin(it)
                onClickCoin { _ ->
                    onClickCoin(it)
                }
            }
        }
    }
}