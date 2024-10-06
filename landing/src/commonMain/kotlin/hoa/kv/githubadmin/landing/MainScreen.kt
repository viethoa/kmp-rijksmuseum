package hoa.kv.githubadmin.landing

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hoa.kv.githubadmin.designsystem.lazycolumn.LazyColumnPaging
import hoa.kv.githubadmin.designsystem.loading.CircularLoadingProgress
import hoa.kv.githubadmin.designsystem.usercard.UserCardView
import hoa.kv.githubadmin.landing.utils.koinViewModel
import hoa.kv.githubadmin.landing.utils.toUserCardViewModel
import hoa.kv.githubadmin.repository.model.User
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel<MainViewModel>(),
    snackbarHostState: SnackbarHostState,
    onUserItemClicked: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(
        lifecycleOwner = LocalLifecycleOwner.current
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar() },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        MainViewContainer(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            snackbarHostState = snackbarHostState,
            uiState = uiState,
            onLoadMore = { viewModel.getMoreUsers() }
        ) { user ->
            onUserItemClicked(user.loginName)
        }
    }
}

@Composable
fun MainViewContainer(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    uiState: MainUiState,
    onLoadMore: () -> Unit,
    onItemUserClicked: (User) -> Unit
) {
    val users = remember { mutableStateListOf<User>() }
    val scope = rememberCoroutineScope()

    MainViewContent(
        modifier = modifier,
        users = users,
        loadMore = { onLoadMore() },
        onItemUserClicked = { user -> onItemUserClicked(user) }
    )

    when (uiState) {
        is MainUiState.Loading -> CircularLoadingProgress()
        is MainUiState.GetUsersSuccess -> {
            users.clear()
            users.addAll(uiState.users)
        }
        is MainUiState.GetUsersError -> {
            val errorMessage = stringResource(uiState.error)
            scope.launch { snackbarHostState.showSnackbar(errorMessage) }
        }
    }
}

@Composable
private fun MainViewContent(
    modifier: Modifier = Modifier,
    users: List<User>,
    loadMore: () -> Unit,
    onItemUserClicked: (User) -> Unit
) {
    LazyColumnPaging(
        modifier = modifier,
        items = users,
        firstItemContent = { user ->
            MainUserCardView(user = user, topPadding = 12) {
                onItemUserClicked(user)
            }
        },
        itemContent = { user ->
            MainUserCardView(user = user, topPadding = 6) {
                onItemUserClicked(user)
            }
        },
        loadMore = loadMore
    )
}

@Composable
private fun MainUserCardView(
    user: User,
    topPadding: Int,
    onItemUserClicked: () -> Unit
) {
    UserCardView(
        modifier = Modifier
            .padding(top = topPadding.dp, bottom = 6.dp, start = 16.dp, end = 16.dp)
            .shadow(
                elevation = 8.dp,
                ambientColor = Color.Black,
                spotColor = Color.Black,
                shape = RoundedCornerShape(8.dp)
            ),
        data = user.toUserCardViewModel(),
        onCardViewClicked = { onItemUserClicked() }
    )
}

@Preview
@Composable
fun PreviewUserCardView() {
    MainViewContent(
        loadMore = {},
        onItemUserClicked = {},
        users = listOf(
            User(
                12,
                "Hoa with text is too long for testing purpose",
                "",
                "https://github.com/Tessalol",
                "Vietnam"
            ),
            User(
                12,
                "Hoa with text is too long for testing purpose",
                "",
                "https://github.com/Tessalol",
                "Vietnam"
            )
        )
    )
}