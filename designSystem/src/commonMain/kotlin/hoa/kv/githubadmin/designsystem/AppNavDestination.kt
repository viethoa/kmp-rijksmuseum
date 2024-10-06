package hoa.kv.githubadmin.designsystem

/**
 * Destinations used in the whole application navigation.
 *
 * @notes for big application better create one module
 * for navigation and define the destination there
 */
sealed class AppNavDestination(val route: String) {
    data object Landing : AppNavDestination("landing")
    data object UserDetails : AppNavDestination("userdetails/{id}")
}