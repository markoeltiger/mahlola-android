package com.mark.mahlola.features.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mark.mahlola.core.base.navigation.BottomNavigationBar

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
){
    Scaffold (
        topBar = {},
        bottomBar = {
                    BottomNavigationBar()
        },
        content = {
            HomeScreenContent(it)
        }
    )

}

@Composable
fun HomeScreenContent(it: PaddingValues) {
     Column (modifier = Modifier
         .fillMaxSize()
         .padding(paddingValues = it)){
            WalletSection()
     }
}

@Preview
@Composable
fun HomeScreenPreview(){

}