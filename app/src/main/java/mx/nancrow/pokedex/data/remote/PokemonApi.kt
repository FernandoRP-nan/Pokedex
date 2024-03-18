package mx.nancrow.pokedex.data.remote

import mx.nancrow.pokedex.domain.model.network.response.BaseResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{id}/")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): PokemonResponse

    @GET("pokemon-species/{id}/")
    suspend fun getPokemonSpecies(
        @Path("id") id: Int
    ): PokemonSpeciesResponse
}
