package mx.nancrow.pokedex.presentation.screens.act1

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.com.satoritech.journeys.presentation.viewmodel.BaseViewModel
import mx.nancrow.pokedex.R
import mx.pokedex.presentation.screens.act1.Act1ViewEvent
import mx.pokedex.presentation.screens.act1.Act1ViewState
import javax.inject.Inject

@HiltViewModel
class Act1ViewModel @Inject constructor(
    application: Application,
) : BaseViewModel(application) {

    var state by mutableStateOf(Act1ViewState())
        private set

    init {
        initViewState(Act1ViewState())
    }

    fun onEvent(event: Act1ViewEvent) {
        when (event) {
            is Act1ViewEvent.OnEmailChange -> onTextChange(event.newText)
            is Act1ViewEvent.UpdateResult -> updateResult(event.newText)
            Act1ViewEvent.OnHiddenDialogError -> showDialogLogout(false)
            Act1ViewEvent.OnShowDialogError -> showDialogLogout(true)
            is Act1ViewEvent.Login -> loginUser()
        }
    }

    private fun loginUser() {
        val result = validateForm()
        if (!result)
            state = state.copy(
                result = "",
            )
        state = state.copy(
            isLoginSuccess = result,
        )

    }

    private fun onTextChange(newText: String) {
        state = state.copy(
            input = newText
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
        val error: Int? = validate(state.input)
        state = state.copy(
            emailError = error,
        )
        return error == null
    }

    private fun showDialogLogout(it: Boolean) {
        state = state.copy(
            showDialogError = it
        )
    }
}



