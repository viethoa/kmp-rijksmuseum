package hoa.kv.githubadmin.repository.network

import hoa.kv.githubadmin.repository.ApiResponse
import hoa.kv.githubadmin.repository.ApiResponse.HttpRequestException
import hoa.kv.githubadmin.repository.ApiResponse.NoNetworkException
import kotlinx.io.IOException

fun <T> Throwable.toApiResponse(): ApiResponse<T> {
    return when (this) {
        is IOException -> {
            ApiResponse.Error(NoNetworkException)
        }
        is IllegalArgumentException -> {
            ApiResponse.Error(this)
        }
        else -> {
            ApiResponse.Error(HttpRequestException("Something went wrong, need to handle http exception"))
        }
    }
}