package mx.pokedex.presentation.screens.act1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.nancrow.R
import mx.nancrow.pokedex.presentation.composables.buttons.BasicButton
import mx.nancrow.pokedex.presentation.screens.act1.Act1ViewModel
import mx.pokedex.presentation.composables.dialogs.DismissDialog
import mx.pokedex.presentation.navigation.Screens
import mx.pokedex.presentation.composables.screens.Screen
import mx.pokedex.presentation.composables.textfields.GenericTextField
import mx.pokedex.presentation.theme.LocalSpacing

@Composable
fun Act1Screen(
    navController: NavController,
    viewModel: Act1ViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    Screen(
        navController = navController,
        buttonBack = false,
        currentRoute = Screens.ACT_1
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = spacing.spaceMedium)
                .padding(horizontal = spacing.spaceLarge)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
            val focusManager = LocalFocusManager.current
            GenericTextField(
                modifier = Modifier.fillMaxWidth(),
                //label = R.string.name,
                hint = R.string.input_activity1,
                onValueTextChange = { viewModel.onEvent(Act1ViewEvent.OnEmailChange(it)) },
                value = viewModel.state.input,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.moveFocus(
                        FocusDirection.Down
                    )
                }),
                isError = viewModel.state.emailError != null,
                errorMessage = viewModel.state.emailError,
                singleLine = true
            )
            val composableScope = rememberCoroutineScope()
            BasicButton(
                modifier = Modifier
                    .height(50.dp),
                label = R.string.calculate,
                onClick = {
                    viewModel.onEvent(Act1ViewEvent.Login)
                    if (viewModel.state.isLoginSuccess) {
                        println("se ejecuto")
                        viewModel.onEvent(
                            Act1ViewEvent.UpdateResult(generarTabla(
                                    viewModel.state.input.toInt()
                                )
                            )
                        )
                    } else {
                        composableScope.launch {
                            viewModel.onEvent(Act1ViewEvent.OnShowDialogError)
                            delay(1000L)
                            viewModel.onEvent(Act1ViewEvent.OnHiddenDialogError)
                        }
                    }
                },
            )
            if (viewModel.state.result != "") {
                Text(
                    text = "${stringResource(id = R.string.result_is)}\n ${viewModel.state.result}",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                )
            }
            if (viewModel.state.showDialogError){
                DismissDialog(message = stringResource(id = R.string.act1_error_required))
            }
        }
    }
}

fun generarTabla(numero: Int): String {
    val builder = StringBuilder()
    for (i in 1..100) {
        val producto = numero * i
        builder.append("$numero * $i = $producto\n")
    }
    return builder.toString()
}