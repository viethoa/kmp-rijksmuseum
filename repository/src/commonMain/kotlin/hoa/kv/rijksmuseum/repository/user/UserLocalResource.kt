package hoa.kv.rijksmuseum.repository.user

import hoa.kv.rijksmuseum.repository.model.User
import hoa.kv.rijksmuseum.repository.model.UserEntity

class UserLocalResource(
    private val userDao: UserDao
) {
    suspend fun getUsers(perPage: Int, since: Int): List<User> {
        return userDao
            .getUsers(perPage, since)
            .map { userEntity ->
                User(userEntity)
            }
    }

    suspend fun insertUsers(users: List<User>, since: Int) {
        users
            .takeIf { it.isNotEmpty() }
            ?.toUserEntities(since)
            ?.let { userEntities ->
                userDao.insertUsers(*userEntities)
            }
    }

    private fun List<User>.toUserEntities(since: Int): Array<UserEntity> {
        return this
            .mapIndexed { index, user ->
                UserEntity(user, since + index)
            }
            .toTypedArray()
    }
}