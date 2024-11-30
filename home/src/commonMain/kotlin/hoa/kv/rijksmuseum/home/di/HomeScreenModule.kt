package hoa.kv.rijksmuseum.home.di

import hoa.kv.rijksmuseum.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeScreenModule = module {
    viewModel<HomeViewModel> { HomeViewModel(get()) }
}