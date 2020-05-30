package com.github.ticktakclock.billinglibrarysample.infra.repository

import com.github.ticktakclock.billinglibrarysample.domain.Coin
import com.github.ticktakclock.billinglibrarysample.domain.repository.CoinRepository
import com.github.ticktakclock.billinglibrarysample.infra.dto.CoinModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoinRepositoryImpl : CoinRepository {
    override fun coins(): Flow<List<Coin>> = flow {
        // get data from server.
        emit(testData)
    }

    private val testData = listOf(
        CoinModel(id = 0, sku = "android.test.purchased", price = 0).toEntity(),
        CoinModel(id = 1, sku = "android.test.canceled", price = 0).toEntity(),
        CoinModel(id = 2, sku = "android.test.item_unavailable", price = 0).toEntity(),
        CoinModel(
            id = 3,
            sku = "com.github.ticktakclock.billinglibrarysample.100yen",
            price = 100
        ).toEntity(),
        CoinModel(
            id = 4,
            sku = "com.github.ticktakclock.billinglibrarysample.200yen",
            price = 200
        ).toEntity()
    )
}