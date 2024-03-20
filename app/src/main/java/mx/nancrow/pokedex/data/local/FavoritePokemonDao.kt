package mx.nancrow.pokedex.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity

@Dao
interface FavoritePokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(userEntity: FavoritePokemonEntity)

    @Delete
    fun deletePokemon(userEntity: FavoritePokemonEntity)

    @Query("SELECT * FROM favorite_pokemon WHERE id = :id")
    fun getFavoritePokemon(id: Int): Flow<FavoritePokemonEntity>

    @Query("SELECT * from favorite_pokemon")
    fun getAllFavoritePokemon(): Flow<List<FavoritePokemonEntity>>

    @Update
    fun updatePokemon(userEntity: FavoritePokemonEntity)

}