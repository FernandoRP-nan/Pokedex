package mx.nancrow.pokedex.domain.repository

import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.util.Resource

interface PokemonRepository {

    suspend fun getPokemon(pokemonId: Int): Resource<PokemonResponse>


}