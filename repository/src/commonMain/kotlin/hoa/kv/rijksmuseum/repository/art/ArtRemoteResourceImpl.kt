package hoa.kv.rijksmuseum.repository.art

import hoa.kv.rijksmuseum.repository.model.NetworkArt
import hoa.kv.rijksmuseum.repository.response.CollectionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val PS = "ps"
private const val PAGING_PAGE_SIZE = 100
const val COLLECTION = "collection"
const val PAGE = "p"

class ArtRemoteResourceImpl(
    private val httpClient: HttpClient,
) : ArtRemoteResource {

    override suspend fun getCollection(page: Int): List<NetworkArt> {
        return httpClient
            .get("$COLLECTION?&$PAGE=$page&$PS=$PAGING_PAGE_SIZE")
            .body<CollectionResponse>().networkArtObjects
    }
}