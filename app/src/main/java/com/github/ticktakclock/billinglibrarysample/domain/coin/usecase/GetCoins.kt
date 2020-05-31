package com.github.ticktakclock.billinglibrarysample.domain.coin.usecase

import com.github.ticktakclock.billinglibrarysample.domain.coin.Coin
import kotlinx.coroutines.flow.Flow

interface GetCoins {
    fun execute(): Flow<List<Coin>>
}