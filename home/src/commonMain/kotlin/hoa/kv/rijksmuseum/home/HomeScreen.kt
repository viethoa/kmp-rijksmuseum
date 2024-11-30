package hoa.kv.rijksmuseum.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hoa.kv.rijksmuseum.designsystem.image.RijksmuseumImage
import hoa.kv.rijksmuseum.designsystem.loading.RijksmuseumLoading
import hoa.kv.rijksmuseum.home.component.ImageDescriptionAndPagerIndicator
import hoa.kv.rijksmuseum.home.model.HomeUiState
import hoa.kv.rijksmuseum.repository.model.Art
import kmp_rijksmuseum.home.generated.resources.Res
import kmp_rijksmuseum.home.generated.resources.home_no_arts
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenRoute(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    onNavigateToCollection: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(
        lifecycleOwner = LocalLifecycleOwner.current
    )
    HomeScreen(
        uiState = uiState,
        onNavigateToCollection = onNavigateToCollection
    )
}

@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    onNavigateToCollection: () -> Unit
) {
    Scaffold { _ ->
        when (uiState) {
            is HomeUiState.Loading -> RijksmuseumLoading()
            is HomeUiState.Success -> collectionView(uiState.arts, onNavigateToCollection)
            is HomeUiState.Error -> errorBox(uiState.message)
            is HomeUiState.Empty -> emptyBox()
        }
    }
}

@Composable
private fun collectionView(arts: List<Art>, onNavigateToCollection: () -> Unit) {
    val pagerState = rememberPagerState(
        pageCount = { arts.size },
        initialPage = 0,
    )
    val currentPage = pagerState.currentPage

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = 2,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            RijksmuseumImage(
                imageUrl = arts[page].webImage.url,
                modifier = Modifier.fillMaxSize(),
            )
        }

        ImageDescriptionAndPagerIndicator(
            art = arts[currentPage],
            pagerState = pagerState,
            onNavigateToCollection = onNavigateToCollection,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
                .padding(bottom = 60.dp)
                .navigationBarsPadding()
        )
    }
}

@Composable
private fun emptyBox() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(Res.string.home_no_arts),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
private fun errorBox(message: StringResource) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(message),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
fun PreviewUserCardView() {

}