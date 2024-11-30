package kv.hoa.rijksmuseum

import androidx.compose.ui.window.ComposeUIViewController
import kv.hoa.rijksmuseum.db.appDataBaseModule
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