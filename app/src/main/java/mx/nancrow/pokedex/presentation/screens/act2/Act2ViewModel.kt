package mx.nancrow.pokedex.presentation.screens.act2

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.com.satoritech.journeys.presentation.viewmodel.BaseViewModel
import mx.nancrow.R
import mx.pokedex.presentation.screens.act2.Act2ViewEvent
import mx.pokedex.presentation.screens.act2.Act2ViewState
import javax.inject.Inject

@HiltViewModel
class Act2ViewModel @Inject constructor(
    application: Application,
) : BaseViewModel(application) {

    var state by mutableStateOf(Act2ViewState())
        private set

    init {
        initViewState(Act2ViewState())
    }

    fun onEvent(event: Act2ViewEvent) {
        when (event) {
            is Act2ViewEvent.OnInput1Change -> onInput1Change(event.newText)
            is Act2ViewEvent.OnInput2Change -> onInput2Change(event.newText)
            is Act2ViewEvent.OnInput3Change -> onInput3Change(event.newText)
            is Act2ViewEvent.OnInput4Change -> onInput4Change(event.newText)
            is Act2ViewEvent.UpdateResult -> updateResult(event.newText)
            is Act2ViewEvent.Calculate -> loginUser()
        }
    }

    private fun loginUser() {
        val condition1 = validateForm()
        val condition2 = validateForm2()
        val condition3 = validateForm3()
        val condition4 = validateForm4()
        state = state.copy(
            isSuccess = condition1,
            isSuccess2 = condition2,
            isSuccess3 = condition3,
            isSuccess4 = condition4
        )
        val result = (validateForm() && validateForm2() && validateForm3() && validateForm4())
        if (!result)
            state = state.copy(
                result = "",
            )
        state = state.copy(
            isAllSuccess = result
        )
    }

    private fun onInput1Change(newText: String) {
        state = state.copy(
            input1 = newText
        )
    }

    private fun onInput2Change(newText: String) {
        state = state.copy(
            input2 = newText
        )
    }

    private fun onInput3Change(newText: String) {
        state = state.copy(
            input3 = newText
        )
    }

    private fun onInput4Change(newText: String) {
        state = state.copy(
            input4 = newText
        )
    }

    private fun updateResult(newText: String) {
        state = state.copy(
            result = newText
        )
    }

    private fun validate(input: String): Int? {
        var error: Int? = null
        val num = input.toIntOrNull()

        if (num == null) {
            error = R.string.act1_error_required
        } else if (num < 1 || num > 100) {
            error = R.string.act1_error_format
        }
        return error
    }

    private fun validateForm(): Boolean {
        val emailError: Int? = validate(state.input1)
        state = state.copy(
                emailError = emailError,
            )
        return emailError == null
    }

    private fun validateForm2(): Boolean {
        val emailError: Int? = validate(state.input2)
        state = state.copy(
            emailError2 = emailError,
        )
        return emailError == null
    }

    private fun validateForm3(): Boolean {
        val emailError: Int? = validate(state.input3)
        state = state.copy(
            emailError3 = emailError,
        )
        return emailError == null
    }

    private fun validateForm4(): Boolean {
        val emailError: Int? = validate(state.input4)
        state = state.copy(
            emailError4 = emailError,
        )
        return emailError == null
    }
    private fun recoverPasswordDialog() {
         state = state.copy(
                recoverPasswordDialog = !state.recoverPasswordDialog
            )
    }
}



