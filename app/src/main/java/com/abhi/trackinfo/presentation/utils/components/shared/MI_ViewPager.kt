package com.abhi.trackinfo.presentation.utils.components.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun MI_ViewPager(
    images: List<String> = emptyList(),
    modifier: Modifier,
    autoScrollIntervalsMs: Long = 3000L,
    onClick: (Int) -> Unit = {}
) {

    if(images.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { images.count() })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(isDragged,images.size) {
        if(!isDragged && images.size>1) {
            while (true) {
                delay(autoScrollIntervalsMs.milliseconds)
                val nextPage = (pagerState.currentPage + 1) % images.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ){
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Card(
                shape = RoundedCornerShape(0.dp),
                colors = CardDefaults.cardColors(
                    contentColor = Color.LightGray.copy(alpha = 0.2f)
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick(page) }
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(images[page])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Carousel images",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        if(images.size>1){
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp)
            ) {
                repeat(images.size){ index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(if (isSelected) 10.dp else 6.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) Color.White else Color.White.copy(alpha = 0.5f)
                            )
                    ) { }
                }
            }
        }
    }

}

@Preview
@Composable
private fun MI_ViewPagerDisplay(){

}
