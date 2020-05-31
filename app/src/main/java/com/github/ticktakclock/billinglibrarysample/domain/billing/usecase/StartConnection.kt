package com.github.ticktakclock.billinglibrarysample.domain.billing.usecase

import com.github.ticktakclock.billinglibrarysample.domain.billing.ConnectionState

interface StartConnection {

    suspend fun execute(): ConnectionState
}