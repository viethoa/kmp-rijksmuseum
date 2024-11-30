package hoa.kv.rijksmuseum.repository.di

import hoa.kv.githubadmin.repository.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val KEY = "key"
const val RIJKS_MUSEUM_CLIENT = "RijksmuseumClient"
const val RIJKSMUSEUM_PATH = "api/en/"
const val HAS_IMAGE = "hasImage"

val networkModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            explicitNulls = false
            prettyPrint = true
        }
    }
    single(named(RIJKS_MUSEUM_CLIENT)) {
        HttpClient {
            install(ContentNegotiation) {
                json(get())
            }
            //install(HttpCache)
            install(Logging) {
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.RIJKSMUSEUM_HOST
                    path(RIJKSMUSEUM_PATH)
                    parameters.append(KEY, BuildConfig.RIJKSMUSEUM_API_KEY)
                    parameters.append(HAS_IMAGE, true.toString())
                }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }
    single {
        HttpClient()
    }
}