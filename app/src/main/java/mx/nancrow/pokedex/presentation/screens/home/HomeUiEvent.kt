package mx.nancrow.pokedex.presentation.screens.home

sealed class HomeUiEvent {
    data object OnLoginSuccess : HomeUiEvent()
    data class ShowSnackBar(val message: String) : HomeUiEvent()
}