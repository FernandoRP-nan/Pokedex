package mx.nancrow.pokedex.presentation.screens.home

import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.pokedex.presentation.viewstate.ViewState

data class HomeViewState(
    val input: String = "",
    val isLoginSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val emailError: Int? = null,
    val result: String = "",
    val showDialogError: Boolean = false,
    val listPokemon: List<PokemonResponse>? = listOf()


) : ViewState()