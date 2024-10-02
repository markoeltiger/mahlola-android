package com.mark.mahlola.features.onboarding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mark.mahlola.core.base.collectEvents
import com.mark.mahlola.core.base.collectState
import com.mark.mahlola.core.ui.ThemedPreview

@Composable
fun OnBoardingScreen(viewModel: OnboardingViewModel, goToAuth:() -> Unit) {
    val state by viewModel.collectState()
    viewModel.collectEvents {
        when (it) {
            OnboardingEvent.GotoAuth -> goToAuth()
        }
    }
    Text(
        text = "Hello onBoarding!",

    )
}
@Preview(device = Devices.PIXEL_4_XL)
@Composable
private fun Preview() {
    ThemedPreview {
        OnBoardingScreen(viewModel = hiltViewModel(), goToAuth = {})
    }
}