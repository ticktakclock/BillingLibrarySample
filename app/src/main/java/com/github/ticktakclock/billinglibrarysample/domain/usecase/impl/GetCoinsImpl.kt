package com.github.ticktakclock.billinglibrarysample.domain.usecase.impl

import com.github.ticktakclock.billinglibrarysample.domain.Coin
import com.github.ticktakclock.billinglibrarysample.domain.repository.CoinRepository
import com.github.ticktakclock.billinglibrarysample.domain.usecase.GetCoins
import kotlinx.coroutines.flow.Flow

class GetCoinsImpl(private val coinRepository: CoinRepository) :
    GetCoins {
    override fun execute(): Flow<List<Coin>> {
        return coinRepository.coins()
    }
}