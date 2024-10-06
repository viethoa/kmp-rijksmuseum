package kv.hoa.githubadmin.di

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hoa.kv.githubadmin.designsystem.AppNavDestination
import hoa.kv.githubadmin.landing.MainScreen
import hoa.kv.githubadmin.userdetails.UserDetailsScreen

/**
 * Main navigation graph for the Art Gallery Viewer app.
 * @param startDestination The starting destination of the NavHost.
 * @param navController The NavController to be used by the NavHost.
 */
@Composable
fun AppNavigationGraph(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    startDestination: AppNavDestination = AppNavDestination.Landing,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        startDestination = startDestination.route,
        navController = navController,
    ) {
        composable(AppNavDestination.Landing.route) {
            MainScreen(
                snackbarHostState = snackbarHostState,
                onUserItemClicked = { userLoginName ->
                    navController.navigate("userdetails/$userLoginName")
                }
            )
        }
        composable(AppNavDestination.UserDetails.route) { entry ->
            val id = entry.arguments
                ?.getString("id")
                ?.let(::requireNotNull)
                .orEmpty()
            UserDetailsScreen(
                userLoginName = id,
                snackbarHostState = snackbarHostState
            ) {
                navController.navigateUp()
            }
        }
    }
}