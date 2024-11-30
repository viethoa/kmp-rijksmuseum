package hoa.kv.rijksmuseum.designsystem.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import hoa.kv.rijksmuseum.designsystem.resources.Res
import hoa.kv.rijksmuseum.designsystem.resources.loading_content_description
import org.jetbrains.compose.resources.stringResource

/**
 * AgvLoading is a composable that displays a loading indicator.
 * @param modifier Modifier to be applied to the loading indicator.
 */
@Composable
fun RijksmuseumLoading(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(stringResource(Res.string.loading_content_description)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}