package hoa.kv.rijksmuseum.repository.response

import hoa.kv.rijksmuseum.repository.model.NetworkArt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionResponse(
    @SerialName("artObjects") var networkArtObjects: ArrayList<NetworkArt>,
)