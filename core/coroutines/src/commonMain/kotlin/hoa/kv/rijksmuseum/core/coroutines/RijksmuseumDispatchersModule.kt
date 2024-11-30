package hoa.kv.rijksmuseum.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

val rijksmuseumDispatchersModule = module {
    single(named(RijksmuseumDispatchers.IO)) { provideIoDispatcher() }
    single(named(RijksmuseumDispatchers.Default)) { Dispatchers.Default }
    single<CoroutineScope> { provideApplicationScope(get(named(RijksmuseumDispatchers.Default))) }
}

private fun provideApplicationScope(dispatcher: CoroutineDispatcher): CoroutineScope =
    CoroutineScope(SupervisorJob() + dispatcher)