package mx.nancrow.pokedex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.nancrow.pokedex.domain.model.network.response.Sprites

@Entity(tableName = "favorite_pokemon")
data class FavoritePokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val sprites: String
)
