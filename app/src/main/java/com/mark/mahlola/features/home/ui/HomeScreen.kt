package com.mark.mahlola.features.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
         val scrollState = rememberScrollState()
         val imageList = listOf(
             "https://static.vecteezy.com/system/resources/thumbnails/001/381/216/small/special-offer-sale-banner-with-megaphone-free-vector.jpg",
             "https://static.vecteezy.com/system/resources/thumbnails/001/381/216/small/special-offer-sale-banner-with-megaphone-free-vector.jpg",
             "https://static.vecteezy.com/system/resources/thumbnails/001/381/216/small/special-offer-sale-banner-with-megaphone-free-vector.jpg"
         )
         WalletSection()
         Column(modifier = Modifier
             .padding(10.dp)
             .verticalScroll(scrollState)) {
             ImageCarousel(imageList)
         }
         CardsSection()
     }
}

@Preview
@Composable
fun HomeScreenPreview(){

}