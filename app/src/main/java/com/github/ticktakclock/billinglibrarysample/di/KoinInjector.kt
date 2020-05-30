package com.github.ticktakclock.billinglibrarysample.di

import com.github.ticktakclock.billinglibrarysample.ui.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MODULE_MAIN = module {
    viewModel { MainViewModel() }
}
