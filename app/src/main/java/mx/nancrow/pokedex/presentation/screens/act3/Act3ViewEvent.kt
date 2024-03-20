package mx.nancrow.pokedex.presentation.screens.act3

sealed interface Act3ViewEvent{
    data class OnClickSettings(val label: Int): Act3ViewEvent

}