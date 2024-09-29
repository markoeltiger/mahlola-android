package com.mark.mahlola.core.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.mark.mahlola.core.ui.theme.LIBroomTheme
import com.mark.mahlola.core.ui.theme.LightColors


@Composable
internal fun ThemedPreview(
    darkTheme: Boolean = false,
    uiMode: UiMode = UiMode.PhonePortrait,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalUiMode provides uiMode) {
        LIBroomTheme(darkTheme = darkTheme) {
            Surface(color = LightColors.surface) {
                content()
            }
        }
    }
}
