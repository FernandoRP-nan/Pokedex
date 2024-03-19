package mx.nancrow.pokedex.presentation.screens.home

sealed interface HomeViewEvent{
    data class OnEmailChange(val newText: String): HomeViewEvent
    object OnHiddenDialogError: HomeViewEvent
    object OnShowDialogError: HomeViewEvent
}