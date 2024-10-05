package com.mark.mahlola

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import com.mark.mahlola.features.onboarding.OnBoardingScreen
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.mark.mahlola.core.theme.BACKGROUND_COLOR
import com.mark.mahlola.features.root.domain.AuthState
import com.mark.mahlola.core.theme.MahlolaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge( statusBarStyle = SystemBarStyle.auto(
            BACKGROUND_COLOR.value.toInt(),
            BACKGROUND_COLOR.value.toInt()
        ),
            navigationBarStyle = SystemBarStyle.auto(
                BACKGROUND_COLOR.value.toInt(),
                BACKGROUND_COLOR.value.toInt()
            ))
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.authState.value == AuthState.UNKNOWN
        }
        actionBar?.hide()
        setContent {
            MahlolaTheme {
                Crossfade(targetState = viewModel.authState.value, label = "scene") { state ->
                    when(state) {
                        AuthState.ONBOARDING -> {
                            OnBoardingScreen(
                                viewModel = hiltViewModel(),
                                goToAuth = {
                                    viewModel.setAuthState(AuthState.UNAUTHENTICATED)
                                }
                            )
                        }
                        AuthState.UNAUTHENTICATED -> {
//                            LoginScreen(viewModel = hiltViewModel()) {
//                                viewModel.setAuthState(AuthState.AUTHENTICATED)
//                            }
                        }
                        AuthState.AUTHENTICATED -> {
                         //   MainScreen()
                        }
                        else -> {
                            Scaffold {
                                Box(modifier = Modifier.padding(it))
                            }
                        }
                    }
                }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MahlolaTheme {
        Greeting("Android")
    }
}}