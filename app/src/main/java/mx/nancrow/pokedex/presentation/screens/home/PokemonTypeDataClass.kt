package mx.nancrow.pokedex.presentation.screens.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import mx.nancrow.pokedex.domain.model.network.response.NamedAPIResource


@Parcelize
data class PokemonType(
    @SerializedName("slot")
    var slot: Int = 0,
    @SerializedName("type")
    var type: NamedAPIResource = NamedAPIResource("")
): Parcelable