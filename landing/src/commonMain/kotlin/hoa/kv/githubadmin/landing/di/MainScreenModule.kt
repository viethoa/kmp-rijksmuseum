package hoa.kv.githubadmin.landing.di

import hoa.kv.githubadmin.landing.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainScreenModule = module {
    viewModel<MainViewModel> { MainViewModel(userRepository = get()) }
}