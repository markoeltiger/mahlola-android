package com.mark.mahlola.features.onboarding

import androidx.annotation.DrawableRes
import com.mark.mahlola.R


data class OnboardingUiState(
    val currentStep: Int = 0,
    val pages: List<PageUiState> = listOf(
        PageUiState(
            title = "Investing  Simplified",
            description = "Dive into a world of endless possibilities.\n" +
                    "Browse through our vast collection of books, spanning genres and topics.",
            image = R.drawable.ic_bull_onboarding1,
            lottieUrl = "https://lottie.host/5689dcfd-627f-455f-a50d-a81693fcf6e7/SUVeIUfO8f.lottie"
        ),
        PageUiState(
            title = "Create Your Reading Haven",
            description = "Personalize your reading experience by adding books to your favorites. With just a tap, build a library of books that speak to you.",
            image = R.drawable.ic_bull_onboarding1,
            lottieUrl = "https://lottie.host/a575d5f6-d97c-4eca-827f-b7304e5cc8a2/3NhjAHU85M.lottie"

        ),
        PageUiState(
            title = "Read or Listen, Your Choice!",
            description = "Enjoy the flexibility of reading or listening to your favorite books. Whether you prefer the tactile sensation of turning pages or the convenience of audiobooks.",
            image = R.drawable.ic_bull_onboarding1,
            lottieUrl = "https://lottie.host/63ebbfdd-a7b0-42c0-9e18-b6168b6376a0/rDwkMFmJ0E.lottie"

        )
    )
) {
    val isLastStep: Boolean
        get() = currentStep == pages.size - 1
}

data class PageUiState(
    val title: String = "",
    val description: String = "",
    @DrawableRes val image: Int = 0,
    val lottieUrl:String =""

)