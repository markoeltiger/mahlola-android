package com.mark.mahlola.features.home.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mark.mahlola.R
import com.mark.mahlola.core.ui.theme.LightColors.Purple40
import com.mark.mahlola.core.ui.theme.LightColors.Purple80
import com.mark.mahlola.core.ui.theme.LightColors.gradientStartColor
import com.mark.mahlola.core.ui.theme.getGradient
@Composable
fun ImageCarousel(images: List<String>) {
    val scrollState = rememberLazyListState()

    LazyRow(
        state = scrollState,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(images) { imageRes ->
            Image(
                painter = rememberAsyncImagePainter(imageRes),
                contentDescription = "Image Carousel",
                modifier = Modifier
                    .height(180.dp)
                    .width(300.dp)
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )
        }
    }
}