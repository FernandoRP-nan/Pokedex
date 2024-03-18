package mx.nancrow.pokedex.presentation.screens.act2

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import mx.nancrow.pokedex.R
import mx.nancrow.pokedex.presentation.composables.buttons.BasicButton
import mx.pokedex.presentation.navigation.Screens
import mx.pokedex.presentation.composables.screens.Screen
import mx.nancrow.pokedex.presentation.composables.textfields.GenericTextField
import mx.pokedex.presentation.screens.act2.Act2ViewEvent
import mx.pokedex.presentation.theme.LocalSpacing

@Composable
fun Act2Screen(
    navController: NavController,
    viewModel: Act2ViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    var resolve by remember { mutableStateOf(false) }
    Screen(
        navController = navController,
        buttonBack = false,
        currentRoute = Screens.ACT_2
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
                onValueTextChange = {
                    if (it != "") {
                        viewModel.onEvent(Act2ViewEvent.OnInput1Change(it))
                    } else {
                        viewModel.onEvent(Act2ViewEvent.OnInput1Change("0"))
                    }
                },
                value = viewModel.state.input1,
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
            GenericTextField(
                modifier = Modifier.fillMaxWidth(),
                //label = R.string.name,
                hint = R.string.input_activity1,
                onValueTextChange = {
                    if (it != "") {
                        viewModel.onEvent(Act2ViewEvent.OnInput2Change(it))
                    } else {
                        viewModel.onEvent(Act2ViewEvent.OnInput2Change("0"))
                    }
                },
                value = viewModel.state.input2,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.moveFocus(
                        FocusDirection.Down
                    )
                }),
                isError = viewModel.state.emailError2 != null,
                errorMessage = viewModel.state.emailError2,
                singleLine = true
            )
            GenericTextField(
                modifier = Modifier.fillMaxWidth(),
                //label = R.string.name,
                hint = R.string.input_activity1,
                onValueTextChange = {
                    if (it != "") {
                        viewModel.onEvent(Act2ViewEvent.OnInput3Change(it))
                    } else {
                        viewModel.onEvent(Act2ViewEvent.OnInput3Change("0"))
                    }
                },
                value = viewModel.state.input3,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.moveFocus(
                        FocusDirection.Down
                    )
                }),
                isError = viewModel.state.emailError3 != null,
                errorMessage = viewModel.state.emailError3,
                singleLine = true
            )
            GenericTextField(
                modifier = Modifier.fillMaxWidth(),
                //label = R.string.name,
                hint = R.string.input_activity1,
                onValueTextChange = {
                    if (it != "") {
                        viewModel.onEvent(Act2ViewEvent.OnInput4Change(it))
                    } else {
                        viewModel.onEvent(Act2ViewEvent.OnInput4Change("0"))
                    }
                },
                value = viewModel.state.input4,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.moveFocus(
                        FocusDirection.Down
                    )
                }),
                isError = viewModel.state.emailError4 != null,
                errorMessage = viewModel.state.emailError4,
                singleLine = true
            )
            BasicButton(
                modifier = Modifier
                    .height(50.dp),
                label = R.string.calculate,
                onClick = {
                    viewModel.onEvent(Act2ViewEvent.Calculate)
                    if (viewModel.state.isAllSuccess) {
                        println("se ejecuto")
                        viewModel.onEvent(
                            Act2ViewEvent.UpdateResult(
                                if (viewModel.state.result == "") {
                                    aprobado(
                                        calcularPromedio(
                                            numero = viewModel.state.input1.toInt(),
                                            numero2 = viewModel.state.input2.toInt(),
                                            numero3 = viewModel.state.input3.toInt(),
                                            numero4 = viewModel.state.input4.toInt()
                                        )
                                    )
                                } else {
                                    ""
                                }
                            )
                        )
                    }
                },
            )
            if (viewModel.state.result != "") {
                Text(
                    text = "${stringResource(id = R.string.prome_is)} ${
                        calcularPromedio(
                            numero = viewModel.state.input1.toInt(),
                            numero2 = viewModel.state.input2.toInt(),
                            numero3 = viewModel.state.input3.toInt(),
                            numero4 = viewModel.state.input4.toInt()
                        )
                    }",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                )
                Text(
                    text = "${stringResource(id = R.string.result_prom_is)} ${
                        viewModel.state.result
                    }",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                )
            }
        }
    }
}

fun calcularPromedio(numero: Int, numero2: Int, numero3: Int, numero4: Int): Int {
    return (numero + numero2 + numero3 + numero4) / 4
}

fun aprobado(promedio: Int): String {
    return if (promedio >= 70)
        "Aprobado"
    else
        "Reprobado"
}