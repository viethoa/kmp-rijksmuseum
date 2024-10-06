package kv.hoa.githubadmin.di

import hoa.kv.githubadmin.landing.di.mainScreenModule
import hoa.kv.githubadmin.repository.di.repositoryModule
import hoa.kv.githubadmin.userdetails.di.userDetailsModule
import org.koin.dsl.module

/**
 * Define the application dependencies
 */
val appModule = module {
    includes(repositoryModule, mainScreenModule, userDetailsModule)
}