package mx.nancrow.pokedex.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import mx.nancrow.pokedex.presentation.screens.home.PokemonType


@Parcelize
data class PokemonSearchResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("types")
    var types: List<PokemonType> = listOf(),
) : Parcelable
