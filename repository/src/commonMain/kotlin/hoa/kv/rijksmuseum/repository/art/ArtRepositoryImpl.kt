package hoa.kv.rijksmuseum.repository.art

import co.touchlab.kermit.Logger
import hoa.kv.rijksmuseum.repository.ApiResponse
import hoa.kv.rijksmuseum.repository.model.Art
import hoa.kv.rijksmuseum.repository.model.ArtObject
import hoa.kv.rijksmuseum.repository.model.NetworkArt
import hoa.kv.rijksmuseum.repository.model.asArtObject
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ArtRepositoryImpl(
    private val artRemoteResource: ArtRemoteResource,
    private val ioDispatcher: CoroutineDispatcher
) : ArtRepository {

    private val log = Logger.withTag(this::class.simpleName!!)

    override suspend fun getCollection(page: Int): ApiResponse<List<Art>> =
        withContext(ioDispatcher) {
            try {
                val collection = artRemoteResource.getCollection(page)
                ApiResponse.Success(
                    collection
                        .filter {
                            it.networkWebImage != null
                        }
                        .map(NetworkArt::asArtObject)
                )
            } catch (e: Exception) {
                log.e(e) { "Error getting collection" }
                ApiResponse.Error(e)
            }
        }

    override suspend fun getArt(objectId: String): ApiResponse<ArtObject> {
        TODO("Not yet implemented")
    }

    override suspend fun downloadImage(url: String, onDownload: (Long, Long?) -> Unit): ByteReadChannel {
        TODO("Not yet implemented")
    }
}