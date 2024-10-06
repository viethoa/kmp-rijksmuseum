package hoa.kv.githubadmin.userdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import githubadmin.userdetails.generated.resources.Res
import githubadmin.userdetails.generated.resources.ic_follower
import githubadmin.userdetails.generated.resources.ic_following
import githubadmin.userdetails.generated.resources.user_detail_blog
import githubadmin.userdetails.generated.resources.user_detail_follower
import githubadmin.userdetails.generated.resources.user_detail_following
import hoa.kv.githubadmin.designsystem.loading.CircularLoadingProgress
import hoa.kv.githubadmin.designsystem.usercard.UserCardView
import hoa.kv.githubadmin.repository.model.User
import hoa.kv.githubadmin.userdetails.utils.toUserCardViewModel
import hoa.kv.githubadmin.userdetails.widget.FollowWidget
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserDetailsScreen(
    viewModel: UserDetailViewModel = koinViewModel<UserDetailViewModel>(),
    snackbarHostState: SnackbarHostState,
    userLoginName: String,
    onBackClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(
        lifecycleOwner = LocalLifecycleOwner.current
    )
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            UserDetailsTopBar { onBackClicked() }
        }
    ) { innerPadding ->
        UserDetailsContainer(
            modifier = Modifier.padding(innerPadding),
            snackbarHostState = snackbarHostState,
            uiState = uiState
        )
    }

    viewModel.getUser(userLoginName)
}

@Composable
fun UserDetailsContainer(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    uiState: UserDetailUiState
) {
    val scope = rememberCoroutineScope()
    when (uiState) {
        is UserDetailUiState.Loading -> CircularLoadingProgress()
        is UserDetailUiState.GetUserSuccess -> {
            UserDetailsContent(user = uiState.user, modifier = modifier)
        }
        is UserDetailUiState.GetUserError -> {
            val errorMessage = stringResource(uiState.errorMessage)
            scope.launch { snackbarHostState.showSnackbar(errorMessage) }
        }
    }
}

@Composable
private fun UserDetailsContent(
    modifier: Modifier = Modifier,
    user: User
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        UserCardView(
            modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 24.dp)
                .shadow(
                    elevation = 12.dp,
                    ambientColor = Color.Black,
                    spotColor = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                ),
            titleFontSize = 20,
            data = user.toUserCardViewModel()
        )
        FollowContent(user.followers, user.following)
        BlogText(user.landingPageUrl)
    }
}

@Composable
private fun FollowContent(followers: Int, following: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 20.dp)
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Center,
    ) {
        VerticalDivider(color = Transparent, modifier = Modifier.weight(1f))
        FollowWidget(
            iconRes = Res.drawable.ic_follower,
            text = stringResource(Res.string.user_detail_follower),
            number = followers
        )
        VerticalDivider(color = Transparent, modifier = Modifier.weight(0.8f))
        FollowWidget(
            iconRes = Res.drawable.ic_following,
            text = stringResource(Res.string.user_detail_following),
            number = following
        )
        VerticalDivider(color = Transparent, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun BlogText(landingPage: String) {
    Text(
        text = stringResource(Res.string.user_detail_blog),
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    Text(
        text = landingPage,
        color = Color.Black,
        fontSize = 12.sp,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .testTag(landingPage)
    )
}

@Preview
@Composable
fun UserDetailsContentPreview() {
    UserDetailsContent(
        user = User(
            12,
            "Hoa with text is too long for testing purpose",
            "avatar",
            "https://github.com/Tessalol-safasdf-sdf-sadfsadf-asdf-asdf-sadfsdaf-afasfsadfd-fsadf",
            "Vietnam",
            123,
            1423
        )
    )
}