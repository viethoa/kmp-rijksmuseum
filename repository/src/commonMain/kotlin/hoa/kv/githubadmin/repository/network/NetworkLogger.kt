package hoa.kv.githubadmin.repository.network

import io.ktor.client.plugins.logging.Logger

class NetworkLogger : Logger {
    override fun log(message: String) {
        println(message)
    }
}