package mx.nancrow.pokedex.data.remote

import mx.nancrow.pokedex.domain.model.network.response.ListPokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonSearchResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon/?offset=0&limit=1302")
    suspend fun getListAllPokemon(

    ): ListPokemonResponse
    @GET("pokemon/")
    suspend fun getListPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ListPokemonResponse
    @GET("pokemon/{id}/")
    suspend fun getPokemon(
        @Path("id") pokemonId: Int
    ): PokemonResponse
    @GET("pokemon/{pokemonName}/")
    suspend fun getPokemonSearch(
        @Path("pokemonName") pokemonName: String
    ): PokemonSearchResponse
    @GET("pokemon/{name}/")
    suspend fun getPokemonByName(
        @Path("name") pokemonName: String
    ): PokemonResponse
    @GET("pokemon-species/{id}/")
    suspend fun getPokemonSpecies(
        @Path("id") pokemonId: Int
    ): PokemonSpeciesResponse
}
