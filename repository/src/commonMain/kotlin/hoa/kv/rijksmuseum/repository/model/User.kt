package hoa.kv.rijksmuseum.repository.model

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class User(
    @SerialName("id")
    val id: Int,
    @SerialName("login")
    val loginName: String,
    @SerialName("avatar_url")
    val avatarUrl: String?,
    @SerialName("html_url")
    val landingPageUrl: String,
    @SerialName("location")
    val location: String? = null,
    @SerialName("followers")
    val followers: Int = 0,
    @SerialName("following")
    val following: Int = 0
) : Parcelable {

    constructor(userLoginName: String) : this(
        id = 0,
        loginName = userLoginName,
        avatarUrl = null,
        landingPageUrl = ""
    )

    constructor(userEntity: UserEntity) : this(
        id = userEntity.id,
        loginName = userEntity.loginName,
        avatarUrl = userEntity.avatarUrl,
        landingPageUrl = userEntity.landingPageUrl
    )

    constructor(data: Map<String, Any>) : this(
        id = data["id"] as? Int ?: 0,
        loginName = data["login"] as? String ?: "",
        avatarUrl = data["avatar_url"] as? String,
        landingPageUrl = data["html_url"] as? String ?: "",
        location = data["location"] as? String,
        followers = data["followers"] as? Int ?: 0,
        following = data["following"] as? Int ?: 0
    )
}