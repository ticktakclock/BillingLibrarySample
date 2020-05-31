package com.github.ticktakclock.billinglibrarysample.domain.coin.impl

import com.github.ticktakclock.billinglibrarysample.domain.coin.Coin
import com.github.ticktakclock.billinglibrarysample.domain.coin.repository.CoinRepository
import com.github.ticktakclock.billinglibrarysample.domain.coin.usecase.GetCoins
import kotlinx.coroutines.flow.Flow

class GetCoinsImpl(private val coinRepository: CoinRepository) :
    GetCoins {
    override fun execute(): Flow<List<Coin>> {
        return coinRepository.coins()
    }
}