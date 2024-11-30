package hoa.kv.rijksmuseum.repository.art

import hoa.kv.rijksmuseum.core.coroutines.RijksmuseumDispatchers
import hoa.kv.rijksmuseum.repository.di.RIJKS_MUSEUM_CLIENT
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val artRepositoryModule = module {
    single<ArtRemoteResource> { ArtRemoteResourceImpl(get(named(RIJKS_MUSEUM_CLIENT))) }
    single<ArtRepository> {
        ArtRepositoryImpl(
            artRemoteResource = get(),
            ioDispatcher = get(named(RijksmuseumDispatchers.IO))
        )
    }
}