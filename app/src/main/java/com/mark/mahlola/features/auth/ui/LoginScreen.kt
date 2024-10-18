package com.mark.mahlola.features.auth.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mark.mahlola.R
import com.mark.mahlola.core.base.collectState
import com.mark.mahlola.core.compose.EditText
import com.mark.mahlola.core.compose.CircledLogo
import com.mark.mahlola.core.compose.FilledNetworkButton
import com.mark.mahlola.core.ui.ThemedPreview

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    gotoMain: () -> Unit
) {
    val state by viewModel.collectState()

    when(state) {
        is LoginState.SuccessState -> gotoMain()
        is LoginState.ErrorState -> {
            Toast.makeText(LocalContext.current, (state as LoginState.ErrorState).msg, Toast.LENGTH_SHORT).show()
        }
        else -> {}
    }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.onboarding_padding)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CircledLogo()
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(R.string.login_screen_title),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )
            LoginForm(
                state = state,
                onLoginClick = { phone->
                    viewModel.action(LoginAction.SignInClick(phone))
                },
                onForgetPasswordClick = { /*TODO*/ }
            )
        }
    }
}

@Composable
private fun LoginForm(
    state: LoginState,
    onLoginClick: (phone: String) -> Unit,
    onForgetPasswordClick: () -> Unit
) {
    val phone = rememberSaveable { mutableStateOf("") }

    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.section_title_margin_bottom)))


    CountryPhonePicker(
        phoneNumber = phone.value,
        { country ->
        // Handle country selection
    }, { phoneNumber ->
         phone.value = phoneNumber    }

    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.field_margin_bottom)))

    val text = when(state) {
        is LoginState.SuccessState -> stringResource(id = R.string.login_success)
        is LoginState.ErrorState -> stringResource(id = R.string.retry_login)
        else -> stringResource(id = R.string.login)
    }
    FilledNetworkButton(
        text = text,
        loading = state == LoginState.LoadingState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.vertical_screen_margin))
    ) {
        onLoginClick(phone.value)
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.section_title_margin_top))) {
        TextButton(
            onClick = onForgetPasswordClick,
            modifier = Modifier.align(alignment = Alignment.CenterEnd)
        ) {
            Text(
                text = stringResource(R.string.forgot_password)
            )
        }
    }
}
@Composable
fun CountryPhonePicker( phoneNumber :String, onCountrySelected: (Country) -> Unit, onPhoneNumberChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Country Picker

        val countries = listOf(
            Country("United States", "US", "+1"),
            Country("Canada", "CA", "+1"),
            Country("United Kingdom", "GB", "+44"),
            Country("Australia", "AU", "+61"),
            Country("Germany", "DE", "+49"),
            Country("Egypt", "EG", "+20")
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = { Text(text = country.name) },
                    onClick = {
                    onCountrySelected(country)
                    expanded = false
                })
            }
        }

        // Phone Number Input
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = countries.get(countries.lastIndex).phoneCode ?: "", modifier = Modifier.padding(end = 8.dp).align(Alignment.CenterVertically))
            TextField(
                value = phoneNumber,
                onValueChange = onPhoneNumberChange,
                modifier = Modifier.weight(1f),
                placeholder = { Text("Enter phone number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}
@Composable
@Preview
fun LoginScreenPreview() {
    ThemedPreview {
        LoginScreen(viewModel = hiltViewModel()) {}
    }
}