package hoa.kv.rijksmuseum.repository.art

import hoa.kv.rijksmuseum.repository.ApiResponse
import hoa.kv.rijksmuseum.repository.model.Art
import hoa.kv.rijksmuseum.repository.model.ArtObject
import io.ktor.utils.io.ByteReadChannel

interface ArtRepository {
    /**
     * Returns a list of [Art]s.
     */
    suspend fun getCollection(page: Int): ApiResponse<List<Art>>

    /**
     * Returns an [ArtObject] with the given [objectId].
     */
    suspend fun getArt(objectId: String): ApiResponse<ArtObject>

    suspend fun downloadImage(url: String, onDownload: (Long, Long?) -> Unit): ByteReadChannel
}