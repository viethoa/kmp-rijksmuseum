package hoa.kv.githubadmin.repository.di

import hoa.kv.githubadmin.repository.db.AppDatabase
import hoa.kv.githubadmin.repository.user.UserDao
import hoa.kv.githubadmin.repository.user.UserLocalResource
import hoa.kv.githubadmin.repository.user.UserRemoteResource
import hoa.kv.githubadmin.repository.user.UserRepository
import hoa.kv.githubadmin.repository.user.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UserDao> { get<AppDatabase>().userDao() }
    single<UserLocalResource> { UserLocalResource(userDao = get()) }
    single<UserRemoteResource> { UserRemoteResource() }
    single<UserRepository> {
        UserRepositoryImpl(userRemoteResource = get(), userLocalResource = get())
    }
}