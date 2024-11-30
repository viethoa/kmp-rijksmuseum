package hoa.kv.rijksmuseum.repository.di

import hoa.kv.rijksmuseum.repository.art.artRepositoryModule
import org.koin.dsl.module

val repositoryModule = module {
    includes(networkModule, artRepositoryModule)

//    single<UserDao> { get<AppDatabase>().userDao() }
//    single<UserLocalResource> { UserLocalResource(userDao = get()) }
//    single<UserRemoteResource> { UserRemoteResource() }
//    single<UserRepository> {
//        UserRepositoryImpl(userRemoteResource = get(), userLocalResource = get())
//    }
}