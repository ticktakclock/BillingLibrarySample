package com.github.ticktakclock.billinglibrarysample.domain.repository

import com.github.ticktakclock.billinglibrarysample.domain.Coin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun coins(): Flow<List<Coin>>
}