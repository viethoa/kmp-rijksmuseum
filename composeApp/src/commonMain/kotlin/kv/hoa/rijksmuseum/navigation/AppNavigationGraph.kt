package kv.hoa.rijksmuseum.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hoa.kv.rijksmuseum.home.HomeScreenRoute
import org.koin.compose.viewmodel.koinViewModel

/**
 * Main navigation graph for the Art Gallery Viewer app.
 * @param startDestination The starting destination of the NavHost.
 * @param navController The NavController to be used by the NavHost.
 */
@Composable
fun AppNavigationGraph(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    startDestination: AppNavDestination = AppNavDestination.Home,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        startDestination = startDestination.route,
        navController = navController,
    ) {
        composable(AppNavDestination.Home.route) {
            HomeScreenRoute {
                // Todo implemented it
            }
        }
    }
}

/**
 * Returns a [ViewModel] scoped to the parent of the current [NavBackStackEntry].
 * This is useful when you want to share a ViewModel between multiple destinations in a navigation graph.
 */
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}