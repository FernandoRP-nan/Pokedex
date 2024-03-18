package mx.nancrow.pokedex.data.repository

import android.util.Log
import mx.nancrow.pokedex.domain.preferences.Preferences
import mx.nancrow.pokedex.data.remote.PokemonApiService
import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.repository.PokemonRepository
import mx.nancrow.pokedex.util.Resource

class PokemonRepositoryImpl(
    private val api: PokemonApiService,
    private val preferences: Preferences
) : PokemonRepository {
    override suspend fun getPokemon(pokemonId: Int): Resource<PokemonResponse> {
        return try {
            val response = api.getPokemon(pokemonId)
            println(pokemonId)
            Resource.Success(response)
        } catch (e: Exception) {
            Log.e("apiError", e.message ?: "Error")
            Resource.Error(e.message ?: "Error")
        }
    }
}