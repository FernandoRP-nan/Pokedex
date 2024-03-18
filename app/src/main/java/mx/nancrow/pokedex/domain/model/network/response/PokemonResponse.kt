package mx.nancrow.pokedex.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sprites(
    @SerializedName("frontDefault")
    val frontDefault: String
): Parcelable

@Parcelize
data class PokemonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("Sprites")
    val sprites: Sprites,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("description")
    var description: String
): Parcelable
