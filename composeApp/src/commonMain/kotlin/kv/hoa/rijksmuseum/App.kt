package kv.hoa.rijksmuseum

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import hoa.kv.rijksmuseum.designsystem.theme.RijksMuseumTheme
import kv.hoa.rijksmuseum.imageloader.asyncImageLoader
import kv.hoa.rijksmuseum.imageloader.enableDiskCache
import kv.hoa.rijksmuseum.navigation.AppNavigationGraph
import org.koin.compose.KoinContext

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App() {
    RijksMuseumTheme {
        KoinContext {
            setSingletonImageLoaderFactory { context ->
                context
                    .asyncImageLoader()
                    .enableDiskCache()
            }
            AppNavigationGraph(snackbarHostState = remember { SnackbarHostState() })
        }
    }
}