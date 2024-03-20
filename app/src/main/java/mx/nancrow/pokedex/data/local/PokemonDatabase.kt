package mx.nancrow.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity

@Database(
    entities = [FavoritePokemonEntity::class],
    version = 1,
)
abstract class PokemonDatabase: RoomDatabase() {

    abstract fun pokemonFavoriteDao(): FavoritePokemonDao

}