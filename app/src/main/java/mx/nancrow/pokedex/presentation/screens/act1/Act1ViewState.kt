package mx.nancrow.pokedex.presentation.screens.act1

import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.pokedex.presentation.viewstate.ViewState

data class Act1ViewState(
    val input: String = "",
    val isLoginSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val emailError: Int? = null,
    val result: String = "",
    val showDialogError: Boolean = false,
    val exist: Boolean = false,
    val pokemon: PokemonResponse? = null
) : ViewState()