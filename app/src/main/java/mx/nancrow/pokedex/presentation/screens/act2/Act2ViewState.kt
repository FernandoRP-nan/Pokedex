package mx.nancrow.pokedex.presentation.screens.act2

import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity
import mx.pokedex.presentation.viewstate.ViewState

data class Act2ViewState(
    val listPokemon: List<FavoritePokemonEntity> = listOf(),

    ) : ViewState()