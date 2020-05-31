package com.github.ticktakclock.billinglibrarysample.di

import com.android.billingclient.api.BillingClient
import com.github.ticktakclock.billinglibrarysample.domain.billing.BillingClientWrapper
import com.github.ticktakclock.billinglibrarysample.domain.billing.impl.*
import com.github.ticktakclock.billinglibrarysample.domain.billing.usecase.*
import com.github.ticktakclock.billinglibrarysample.domain.coin.impl.GetCoinsImpl
import com.github.ticktakclock.billinglibrarysample.domain.coin.repository.CoinRepository
import com.github.ticktakclock.billinglibrarysample.domain.coin.usecase.GetCoins
import com.github.ticktakclock.billinglibrarysample.infra.repository.CoinRepositoryImpl
import com.github.ticktakclock.billinglibrarysample.ui.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MODULE_MAIN = module {
    single<CoinRepository> { CoinRepositoryImpl() }
    single<GetCoins> {
        GetCoinsImpl(
            get()
        )
    }
    single<GetSku> { GetSkuImpl(get()) }
    single<StartConnection> { StartConnectionImpl(get()) }
    single<StartBilling> { StartBillingImpl(get()) }
    single<GetPurchases> { GetPurchasesImpl(get()) }
    single<ConsumePurchase> { ConsumePurchaseImpl(get()) }
    single { BillingClient.newBuilder(androidContext()) }
    single { BillingClientWrapper(get()) }
    viewModel { MainViewModel(get(), get(), get(), get(), get(), get()) }
}
