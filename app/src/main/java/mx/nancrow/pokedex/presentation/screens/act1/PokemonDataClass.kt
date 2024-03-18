package mx.nancrow.pokedex.presentation.screens.act1

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import mx.nancrow.pokedex.domain.model.network.response.Sprites

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
    var description: String = ""
): Parcelable
