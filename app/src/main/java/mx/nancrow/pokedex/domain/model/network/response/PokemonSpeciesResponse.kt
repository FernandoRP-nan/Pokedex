package mx.nancrow.pokedex.domain.model.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Language(
    val name: String
) : Parcelable


@Parcelize
data class FlavorTextEntry(
    val flavorText: String,
    val language: Language
): Parcelable

@Parcelize
data class PokemonSpeciesResponse(
    val flavorTextEntries: List<FlavorTextEntry>
): Parcelable

