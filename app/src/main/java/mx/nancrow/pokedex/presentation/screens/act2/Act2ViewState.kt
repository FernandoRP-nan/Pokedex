package mx.pokedex.presentation.screens.act2

import mx.pokedex.presentation.viewstate.ViewState

data class Act2ViewState(
    val input1: String = "",
    val input2: String = "",
    val input3: String = "",
    val input4: String = "",
    val password: String = "",
    val isSuccess: Boolean = false,
    val isSuccess2: Boolean = false,
    val isSuccess3: Boolean = false,
    val isSuccess4: Boolean = false,
    val isAllSuccess: Boolean = false,
    val isLoading4: Boolean = false,
    val emailError: Int? = null,
    val emailError2: Int? = null,
    val emailError3: Int? = null,
    val emailError4: Int? = null,
    val passwordError: Int? = null,
    val recoverPasswordDialog: Boolean = false,
    val successfulRecoveryDialog: Boolean = false,
    val emailToRecover: String = "",
    val emailToRecoverError: Int? = null,
    val result: String = ""

) : ViewState()