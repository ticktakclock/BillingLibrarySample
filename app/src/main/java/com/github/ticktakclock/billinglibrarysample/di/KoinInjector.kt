package com.github.ticktakclock.billinglibrarysample.di

import com.github.ticktakclock.billinglibrarysample.domain.coin.impl.GetCoinsImpl
import com.github.ticktakclock.billinglibrarysample.domain.coin.repository.CoinRepository
import com.github.ticktakclock.billinglibrarysample.domain.coin.usecase.GetCoins
import com.github.ticktakclock.billinglibrarysample.infra.repository.CoinRepositoryImpl
import com.github.ticktakclock.billinglibrarysample.ui.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MODULE_MAIN = module {
    single<CoinRepository> { CoinRepositoryImpl() }
    single<GetCoins> {
        GetCoinsImpl(
            get()
        )
    }
    viewModel { MainViewModel(get()) }
}
