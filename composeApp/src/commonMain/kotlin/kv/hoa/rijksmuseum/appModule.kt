package kv.hoa.rijksmuseum

import coil3.annotation.ExperimentalCoilApi
import coil3.network.CacheStrategy
import coil3.network.NetworkFetcher
import coil3.network.ktor3.asNetworkClient
import hoa.kv.rijksmuseum.core.coroutines.rijksmuseumDispatchersModule
import hoa.kv.rijksmuseum.home.di.homeScreenModule
import hoa.kv.rijksmuseum.repository.di.repositoryModule
import io.ktor.client.HttpClient
import org.koin.dsl.module

/**
 * Define the application dependencies
 */
@OptIn(ExperimentalCoilApi::class)
val appModule = module {
    // Coroutines
    includes(rijksmuseumDispatchersModule)
    // Repositories
    includes(repositoryModule)
    // Screens
    includes(homeScreenModule)

    single {
        NetworkFetcher.Factory(
            networkClient = { get<HttpClient>().asNetworkClient() },
            cacheStrategy = { CacheStrategy() },
        )
    }
}