package mx.nancrow.pokedex.presentation.screens.act1

sealed class PokemonUiEvent {
    data object OnLoginSuccess : PokemonUiEvent()
    data class ShowSnackBar(val message: String) : PokemonUiEvent()
}