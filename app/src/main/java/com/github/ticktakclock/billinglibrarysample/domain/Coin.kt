package com.github.ticktakclock.billinglibrarysample.domain

data class Coin(val id: Int, val sku: String, val price: Int) {
    fun withUnit(): String {
        return "$price 円"
    }
}