package com.mark.mahlola.features.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mark.mahlola.core.ui.theme.LightColors.gradientStartColor

@Preview
@Composable
fun WalletSection(){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically) {
    Column() {
        Text(text = "Wallet", fontSize = 17.sp, color = Color.DarkGray)
        Spacer(modifier =Modifier.height(8.dp))
        Text(text = "$44.121", fontSize = 24.sp, color = Color.Black, fontWeight = FontWeight.Bold)
    }
        Box(modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(gradientStartColor)
            .clickable {}
            .padding(6.dp)){
            Icon(imageVector =  Icons.Rounded.Search, contentDescription = "Search Icon ", tint = Color.Black
            )
        }
    }
}