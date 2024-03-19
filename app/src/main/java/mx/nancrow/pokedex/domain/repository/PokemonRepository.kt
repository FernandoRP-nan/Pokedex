package mx.nancrow.pokedex.domain.repository

import mx.nancrow.pokedex.domain.model.network.response.ListPokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonSearchResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonSpeciesResponse
import mx.nancrow.pokedex.util.Resource

interface PokemonRepository {

    suspend fun getPokemon(pokemonId: Int): Resource<PokemonResponse>
    suspend fun getPokemonSearch(pokemonName: String): Resource<PokemonSearchResponse>

    suspend fun getPokemonByName(pokemonName: String): Resource<PokemonResponse>

    suspend fun getPokemonSpecies(pokemonId: Int): Resource<PokemonSpeciesResponse>

    suspend fun getListPokemon(
        limit: Int, offset: Int
    ): Resource<ListPokemonResponse>
    suspend fun getListAllPokemon(
    ): Resource<ListPokemonResponse>
}