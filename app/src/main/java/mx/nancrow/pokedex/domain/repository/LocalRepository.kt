package mx.nancrow.pokedex.domain.repository

import kotlinx.coroutines.flow.Flow
import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity

interface LocalRepository {

    suspend fun insertUser(pokemonEntity: FavoritePokemonEntity)

    suspend fun deleteUser(pokemonEntity: FavoritePokemonEntity)

    fun getFavoritePokemon(id: Int): Flow<FavoritePokemonEntity>

    fun getAllFavoritePokemon(): Flow<List<FavoritePokemonEntity>>


    suspend fun updatePokemon(pokemonEntity: FavoritePokemonEntity)

}