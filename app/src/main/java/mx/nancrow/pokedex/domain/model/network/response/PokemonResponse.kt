package mx.nancrow.pokedex.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import mx.nancrow.pokedex.presentation.screens.home.PokemonAbility
import mx.nancrow.pokedex.presentation.screens.home.PokemonStat
import mx.nancrow.pokedex.presentation.screens.home.PokemonType

@Parcelize
data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String = ""
) : Parcelable

@Parcelize
data class PokemonResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("sprites")
    val sprites: Sprites = Sprites(),
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("weight")
    val weight: Int = 0,
    @SerializedName("description")
    var description: String = "",
    @SerializedName("types")
    var types: List<PokemonType> = listOf(),
    @SerializedName("abilities")
    var abilities: List<PokemonAbility> = listOf(),
    @SerializedName("stats")
    var stats: List<PokemonStat> = listOf(),
) : Parcelable
