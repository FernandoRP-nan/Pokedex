package mx.nancrow.pokedex.presentation.screens.act1

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import mx.nancrow.pokedex.domain.model.network.response.Sprites
import mx.nancrow.pokedex.presentation.screens.home.PokemonAbility
import mx.nancrow.pokedex.presentation.screens.home.PokemonStat
import mx.nancrow.pokedex.presentation.screens.home.PokemonType

@Parcelize
data class PokemonDataClass(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("sprites")
    var sprites: Sprites = Sprites(""),
    @SerializedName("height")
    var height: Int = 0,
    @SerializedName("weight")
    var weight: Int = 0,
    @SerializedName("description")
    var description: String = "",
    @SerializedName("types")
    var types: List<PokemonType> = listOf(),
    @SerializedName("abilities")
    var abilities: List<PokemonAbility> = listOf(),
    @SerializedName("stats")
    var stats: List<PokemonStat> = listOf(),
) : Parcelable
