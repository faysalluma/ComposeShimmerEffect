package com.groupec.shimmereffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.groupec.shimmereffect.ui.theme.ShimmerEffectTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShimmerEffectTheme {
                var isLoading by remember {
                    mutableStateOf(true)
                }
                LaunchedEffect(key1 = true) {
                    delay(5 * 1000)
                    isLoading = false
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(20) {
                        ShimmerItem(isLoading = isLoading)
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerItem(isLoading: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_supervised_user_circle_24),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(60.dp)
                .clip(CircleShape)
                .shimmerEffect(isLoading)
                .alpha(if (isLoading) 0f else 1f)
        )
        Spacer(
            modifier = Modifier
                .width(8.dp)
                .background(Color.Gray)
        )
        Column(modifier = Modifier
            .fillMaxHeight()
            .align(Alignment.CenterVertically)) {
            Text(
                text = "Name- MR. Ravi Sahu",
                modifier = Modifier
                    .shimmerEffect(isLoading)
                    .alpha(if (isLoading) 0f else 1f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Age- 24 ",
                modifier = Modifier
                    .shimmerEffect(isLoading)
                    .alpha(if (isLoading) 0f else 1f)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Contact here -  ",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .shimmerEffect(isLoading)
                .alpha(if (isLoading) 0f else 1f)
        )

    }
}

private fun Modifier.shimmerEffect(toShow: Boolean): Modifier = composed {
    if (toShow) {
        var size by remember {
            mutableStateOf(IntSize.Zero)
        }
        val transition = rememberInfiniteTransition(label = "")
        val startOffsetX by transition.animateFloat(
            initialValue = -2 * size.width.toFloat(),
            targetValue = 2 * size.width.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(1000)
            ), label = ""
        )
        background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFB8B5B5),
                    Color(0xFF8F8B8B),
                    Color(0xFFB8B5B5),
                ),
                start = Offset(startOffsetX, 0f),
                end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
            )
        )
            .onGloballyPositioned {
                size = it.size
            }
    } else {
        Modifier
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShimmerEffectTheme {
        Greeting("Hello")
    }
}