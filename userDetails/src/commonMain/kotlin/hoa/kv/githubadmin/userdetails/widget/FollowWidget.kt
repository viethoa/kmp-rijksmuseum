package hoa.kv.githubadmin.userdetails.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import githubadmin.userdetails.generated.resources.Res
import githubadmin.userdetails.generated.resources.ic_following
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FollowWidget(
    modifier: Modifier = Modifier,
    testTag: String = "",
    iconRes: DrawableResource,
    text: String,
    number: Int
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = "follow-widget-icon",
            modifier = Modifier
                .padding(14.dp)
                .size(24.dp)
                .drawBehind {
                    drawCircle(
                        color = Color.LightGray,
                        radius = this.size.maxDimension
                    )
                }
        )
        Text(
            text = "$number+",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 4.dp)
                .testTag(testTag)
        )
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview
@Composable
fun FollowWidgetPreview() {
    FollowWidget(iconRes = Res.drawable.ic_following, text = "Following", number = 123)
}