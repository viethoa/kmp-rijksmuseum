package hoa.kv.githubadmin.userdetails.di

import hoa.kv.githubadmin.userdetails.UserDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val userDetailsModule = module {
    viewModel<UserDetailViewModel> { UserDetailViewModel(userRepository = get()) }
}