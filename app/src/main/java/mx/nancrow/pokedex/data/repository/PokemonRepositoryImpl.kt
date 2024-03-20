package mx.nancrow.pokedex.data.repository

import android.util.Log
import mx.nancrow.pokedex.domain.preferences.Preferences
import mx.nancrow.pokedex.data.remote.PokemonApiService
import mx.nancrow.pokedex.domain.model.network.response.ListPokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonSearchResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonSpeciesResponse
import mx.nancrow.pokedex.domain.repository.PokemonRepository
import mx.nancrow.pokedex.util.Resource

class PokemonRepositoryImpl(
    private val api: PokemonApiService,
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

    override suspend fun getPokemonSearch(pokemonName: String): Resource<PokemonSearchResponse> {
        return try {
            val response = api.getPokemonSearch(pokemonName)
            println(pokemonName)
            Resource.Success(response)
        } catch (e: Exception) {
            Log.e("apiError", e.message ?: "Error")
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun getPokemonByName(pokemonName: String): Resource<PokemonResponse> {
        return try {
            val response = api.getPokemonByName(pokemonName)
            println(pokemonName)
            Resource.Success(response)
        } catch (e: Exception) {
            Log.e("apiError", e.message ?: "Error")
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun getPokemonSpecies(pokemonId: Int): Resource<PokemonSpeciesResponse> {
        return try {
            val response = api.getPokemonSpecies(pokemonId)
            println(pokemonId)
            Resource.Success(response)
        } catch (e: Exception) {
            Log.e("apiError", e.message ?: "Error")
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun getListPokemon(
        limit: Int, offset: Int
    ): Resource<ListPokemonResponse> {
        return try {
            val response = api.getListPokemon(limit, offset)
            Resource.Success(response)
        } catch (e: Exception) {
            Log.e("apiError", e.message ?: "Error")
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun getListAllPokemon(
    ): Resource<ListPokemonResponse> {
        return try {
            val response = api.getListAllPokemon()
            Resource.Success(response)
        } catch (e: Exception) {
            Log.e("apiError", e.message ?: "Error")
            Resource.Error(e.message ?: "Error")
        }
    }
}