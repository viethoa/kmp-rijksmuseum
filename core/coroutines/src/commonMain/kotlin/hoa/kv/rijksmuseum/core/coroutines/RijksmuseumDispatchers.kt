package hoa.kv.rijksmuseum.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher

enum class RijksmuseumDispatchers {
    IO,
    Default
}

expect fun provideIoDispatcher(): CoroutineDispatcher