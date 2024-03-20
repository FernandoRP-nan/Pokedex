package mx.nancrow.pokedex.presentation.screens.act2

import mx.nancrow.pokedex.presentation.screens.act3.Act3ViewEvent

sealed interface Act2ViewEvent{
    data class OnInput1Change(val newText: String): Act2ViewEvent
    data class OnInput2Change(val newText: String): Act2ViewEvent
    data class OnInput3Change(val newText: String): Act2ViewEvent
    data class OnInput4Change(val newText: String): Act2ViewEvent
    data class UpdateResult(val newText: String): Act2ViewEvent
    object Calculate: Act2ViewEvent
}