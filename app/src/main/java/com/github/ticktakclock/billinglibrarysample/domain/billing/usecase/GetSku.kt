package com.github.ticktakclock.billinglibrarysample.domain.billing.usecase

import com.github.ticktakclock.billinglibrarysample.domain.billing.Sku
import kotlinx.coroutines.flow.Flow


interface GetSku {
    fun execute(sku: String): Flow<Sku>
}