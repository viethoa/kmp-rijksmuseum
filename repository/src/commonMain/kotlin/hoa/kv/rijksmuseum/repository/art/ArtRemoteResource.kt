package hoa.kv.rijksmuseum.repository.art

import hoa.kv.rijksmuseum.repository.model.NetworkArt

interface ArtRemoteResource {
    suspend fun getCollection(page: Int): List<NetworkArt>
}