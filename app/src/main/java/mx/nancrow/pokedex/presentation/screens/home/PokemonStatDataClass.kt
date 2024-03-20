package mx.nancrow.pokedex.presentation.screens.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import mx.nancrow.pokedex.domain.model.network.response.NamedAPIResource


@Parcelize
data class PokemonStat(
    @SerializedName("base_stat")
    var base_stat: Int = 0,
    @SerializedName("stat")
    var stat: NamedAPIResource = NamedAPIResource("")
): Parcelable