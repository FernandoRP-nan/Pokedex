package mx.nancrow.pokedex.domain.repository

import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonSpeciesResponse
import mx.nancrow.pokedex.util.Resource

interface PokemonRepository {

    suspend fun getPokemon(pokemonId: Int): Resource<PokemonResponse>

    suspend fun getPokemonSpecies(pokemonId: Int): Resource<PokemonSpeciesResponse>

}