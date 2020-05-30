package com.github.ticktakclock.billinglibrarysample.domain.usecase

import com.github.ticktakclock.billinglibrarysample.domain.Coin
import kotlinx.coroutines.flow.Flow

interface GetCoins {
    fun execute(): Flow<List<Coin>>
}