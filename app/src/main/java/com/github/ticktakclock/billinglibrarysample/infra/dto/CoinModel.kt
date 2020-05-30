package com.github.ticktakclock.billinglibrarysample.infra.dto

import com.github.ticktakclock.billinglibrarysample.domain.Coin

data class CoinModel(val id: Int, val sku: String, val price: Int) {

    fun toEntity(): Coin {
        return Coin(
            id = id,
            sku = sku,
            price = price
        )
    }
}