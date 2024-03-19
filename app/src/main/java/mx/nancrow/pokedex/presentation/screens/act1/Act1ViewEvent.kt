package mx.nancrow.pokedex.presentation.screens.act1

sealed interface Act1ViewEvent{
    data class OnEmailChange(val newText: String): Act1ViewEvent
    data class UpdateResult(val newText: String): Act1ViewEvent
    object OnHiddenDialogError: Act1ViewEvent
    object OnShowDialogError: Act1ViewEvent
    object Login: Act1ViewEvent
    data class  GetPokemon(val pokemonId: Int): Act1ViewEvent
}