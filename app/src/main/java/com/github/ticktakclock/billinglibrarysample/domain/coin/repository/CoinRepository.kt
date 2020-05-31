package com.github.ticktakclock.billinglibrarysample.domain.coin.repository

import com.github.ticktakclock.billinglibrarysample.domain.coin.Coin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun coins(): Flow<List<Coin>>
}