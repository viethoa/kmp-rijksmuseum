package kv.hoa.githubadmin.di

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import hoa.kv.githubadmin.designsystem.theme.GithubAdminTheme

@Composable
fun App() {
    GithubAdminTheme {
        AppNavigationGraph(snackbarHostState = remember { SnackbarHostState() })
    }
}