package hoa.kv.githubadmin.designsystem.usercard

data class UserCardViewModel(
    val name: String,
    val avatarUrl: String,
    val landingPageUrl: String?,
    val location: String?
)