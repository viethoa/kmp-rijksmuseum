package kv.hoa.githubadmin

import androidx.compose.ui.window.ComposeUIViewController
import kv.hoa.githubadmin.db.appDataBaseModule
import kv.hoa.githubadmin.di.App
import kv.hoa.githubadmin.di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin {
            modules(appDataBaseModule, appModule)
        }
    }
) {
    App()
}