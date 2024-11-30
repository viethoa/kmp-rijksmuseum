package hoa.kv.rijksmuseum.core.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual fun provideIoDispatcher() = Dispatchers.IO