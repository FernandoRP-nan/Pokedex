package mx.pokedex.presentation.screens.act1

import mx.pokedex.presentation.viewstate.ViewState

data class Act1ViewState(
    val input: String = "",
    val isLoginSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val emailError: Int? = null,
    val result: String = "",
    val showDialogError: Boolean = false,

) : ViewState()