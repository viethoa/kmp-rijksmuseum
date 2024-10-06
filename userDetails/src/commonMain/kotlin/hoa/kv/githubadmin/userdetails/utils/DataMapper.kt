package hoa.kv.githubadmin.userdetails.utils

import hoa.kv.githubadmin.designsystem.usercard.UserCardViewModel
import hoa.kv.githubadmin.repository.model.User

/**
 * Copy [User] data to [UserCardViewModel] data.
 * [UserCardViewModel.landingPageUrl] will be empty as the business logic is not showing LandingPage url
 * in UserCard in Details Screen
 */
internal fun User.toUserCardViewModel(): UserCardViewModel {
    return UserCardViewModel(
        name = this.loginName,
        avatarUrl = this.avatarUrl.orEmpty(),
        location = this.location,
        landingPageUrl = ""
    )
}