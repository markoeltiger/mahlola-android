package com.mark.mahlola.features.home.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mark.mahlola.R
import com.mark.mahlola.core.ui.theme.LightColors.Purple40
import com.mark.mahlola.core.ui.theme.LightColors.Purple80
import com.mark.mahlola.core.ui.theme.LightColors.gradientStartColor
import com.mark.mahlola.core.ui.theme.getGradient

val cards = listOf(
    CardItem(
        cardType = "VISA",
        cardName = "Business",
        cardNumber = "3121 1221 2133 1221",
        color = getGradient(Purple40, Purple80),
        balance = "213.12"
    ),
    CardItem(
        cardType = "VISA",
        cardName = "Business",
        cardNumber = "3121 1221 2133 1221",
        color = getGradient(Purple40, Purple80),
        balance = "213.12"
    ),
    CardItem(
        cardType = "VISA",
        cardName = "Business",
        cardNumber = "3121 1221 2133 1221",
        color = getGradient(Purple40, Purple80),
        balance = "213.12"
    )
)

@Preview
@Composable
fun CardsSection() {
    LazyRow {
        items(cards.size) { index ->
            CardItemUI(cards[index],index)

        }
    }
}
@Composable
fun CardItemUI(cardItem: CardItem,
               index: Int){
var lastItemPadding = 0.dp
    if (index == cards.size - 1) {
        lastItemPadding=16.dp
    }
    var image= painterResource(id = R.drawable.ic_home)
    Box(modifier = Modifier.padding(start = 16.dp, end = lastItemPadding)) {
    Column(modifier = Modifier
        .clip(RoundedCornerShape(25.dp))
        .background(cardItem.color)
        .width(250.dp)
        .height(160.dp)
        .clickable { }
        .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Image(painter = image, contentDescription = cardItem.cardName)
        Text(text = cardItem.cardName, color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        Text(text = cardItem.cardNumber, fontSize = 17.sp, )
        Text(text = cardItem.cardType, fontSize = 17.sp, fontWeight = FontWeight.Bold)

    }
    }
}