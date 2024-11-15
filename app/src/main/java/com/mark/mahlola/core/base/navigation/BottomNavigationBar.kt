package com.mark.mahlola.core.base.navigation

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mark.mahlola.core.ui.theme.LightColors.background
import com.mark.mahlola.core.ui.theme.LightColors.gradientMiddleColor
import com.mark.mahlola.core.ui.theme.LightColors.gradientStartColor
import com.mark.mahlola.core.ui.theme.LightColors.primaryContainer

val items=listOf(BottomNavigationItem("Home",Icons.Rounded.Home), BottomNavigationItem("Wallet",Icons.Rounded.List), BottomNavigationItem("Notifications",Icons.Rounded.Notifications),BottomNavigationItem("Account",Icons.Rounded.AccountCircle))
@Composable
fun BottomNavigationBar(){
    NavigationBar {
        Row (modifier = Modifier.background(gradientStartColor)){
            items.forEachIndexed { index, bottomNavigationItem ->
                NavigationBarItem(selected =index==0
                    , onClick ={}
                    , icon ={
                        Icon(
                            imageVector = bottomNavigationItem.icon,
                            contentDescription = bottomNavigationItem.title,
                            tint = gradientMiddleColor
                        )
                    }
                    , label = {Text(bottomNavigationItem.title)}
                )
            }
        }
    }
}