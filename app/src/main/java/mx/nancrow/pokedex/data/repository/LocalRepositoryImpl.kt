package mx.nancrow.pokedex.data.repository

import kotlinx.coroutines.flow.Flow
import mx.nancrow.pokedex.data.local.FavoritePokemonDao
import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity
import mx.nancrow.pokedex.domain.repository.LocalRepository

class LocalRepositoryImpl(
    private val favoritePokemonDao: FavoritePokemonDao,
): LocalRepository {
    override suspend fun insertUser(pokemonEntity: FavoritePokemonEntity) {
        favoritePokemonDao.insertPokemon(pokemonEntity)
    }

    override suspend fun deleteUser(pokemonEntity: FavoritePokemonEntity) {
        favoritePokemonDao.deletePokemon(pokemonEntity)
    }

    override fun getFavoritePokemon(id: Int): Flow<FavoritePokemonEntity> {
        return favoritePokemonDao.getFavoritePokemon(id)
    }

    override fun getAllFavoritePokemon(): Flow<List<FavoritePokemonEntity>> {
        return favoritePokemonDao.getAllFavoritePokemon()
    }

    override suspend fun updatePokemon(pokemonEntity: FavoritePokemonEntity) {
        favoritePokemonDao.updatePokemon(pokemonEntity)
    }

}