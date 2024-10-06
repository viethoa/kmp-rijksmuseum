package hoa.kv.githubadmin.landing.utils

import hoa.kv.githubadmin.designsystem.usercard.UserCardViewModel
import hoa.kv.githubadmin.repository.model.User

/**
 * Copy [User] data to [UserCardViewModel] data.
 * [UserCardViewModel.location] will be empty
 * as the business logic is not showing location in Main Screen
 */
internal fun User.toUserCardViewModel(): UserCardViewModel {
    return UserCardViewModel(
        name = this.loginName,
        avatarUrl = this.avatarUrl.orEmpty(),
        landingPageUrl = this.landingPageUrl,
        location = ""
    )
}