package com.mark.mahlola.features.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mark.mahlola.R
import com.mark.mahlola.core.base.collectEvents
import com.mark.mahlola.core.base.collectState
import com.mark.mahlola.core.theme.appBoldFontFamily
import com.mark.mahlola.core.theme.appReguralFontFamily
import com.mark.mahlola.core.theme.gradientEndColor
import com.mark.mahlola.core.theme.gradientMiddleColor
import com.mark.mahlola.core.theme.gradientStartColor
import com.mark.mahlola.core.ui.ThemedPreview

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnBoardingScreen(viewModel: OnboardingViewModel, goToAuth: () -> Unit) {
    val state by viewModel.collectState()
    viewModel.collectEvents {
        when (it) {
            OnboardingEvent.GotoAuth -> goToAuth()
        }
    }
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(colorResource(id = R.color.background_color))
                .paint(painterResource(id = R.drawable.ic_onboarding_background)),


        ) {
            Pager(state = state)

        }

    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColumnScope.Pager(state: OnboardingState) {
val pagerState = rememberPagerState(pageCount={state.uiState.pages.size})
    LaunchedEffect(key1 = state.uiState.currentStep) {
        pagerState.animateScrollToPage(state.uiState.currentStep)
    }
    HorizontalPager(state = pagerState,
        modifier= Modifier
            .fillMaxSize()
            .weight(1f), userScrollEnabled =true
    ) {page->
    val pageUiState=state.uiState.pages[page]
Column (        horizontalAlignment = Alignment.CenterHorizontally,

verticalArrangement = Arrangement.Top ){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.section_title_margin_top)),
        textAlign = TextAlign.Center,
        text = pageUiState.title,
         fontFamily = appBoldFontFamily,
        style = TextStyle(brush = Brush.linearGradient(colors = listOf(gradientStartColor, gradientMiddleColor,gradientEndColor)), fontSize = 40.sp)

    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding( dimensionResource(id = R.dimen.vertical_screen_margin)),
        textAlign = TextAlign.Center,
        fontFamily = appReguralFontFamily,
        text = pageUiState.description,
        style = MaterialTheme.typography.bodyMedium
    )

    Image(
        modifier = Modifier.size(400.dp), painter = painterResource(id = pageUiState.image),
        contentDescription = "onboard image"
    )

}
    }
}

@Preview(device = Devices.PIXEL_4_XL)
@Composable
private fun Preview() {
    ThemedPreview {
        OnBoardingScreen(viewModel = hiltViewModel(), goToAuth = {})
    }
}