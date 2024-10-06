package hoa.kv.githubadmin.repository.user

import hoa.kv.githubadmin.repository.model.User
import hoa.kv.githubadmin.repository.BuildConfig
import hoa.kv.githubadmin.repository.network.NetworkBase
import io.ktor.client.call.body
import io.ktor.client.request.get

class UserRemoteResource : NetworkBase() {

    suspend fun getUsers(perPage: Int, since: Int): List<User> {
        return httpClient
            .get("${BuildConfig.API_ENDPOINT}$USER_PATH?per_page=$perPage&since=$since")
            .body()
    }

    suspend fun getUser(loginUserName: String): User {
        return httpClient
            .get("${BuildConfig.API_ENDPOINT}$USER_PATH/$loginUserName")
            .body()
    }

    companion object {
        private const val USER_PATH = "/users"
    }
}