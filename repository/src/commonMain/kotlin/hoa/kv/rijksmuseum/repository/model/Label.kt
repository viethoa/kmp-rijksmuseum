package hoa.kv.rijksmuseum.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Label(
    @SerialName("title") var title: String?,
    @SerialName("description") var description: String?,
)