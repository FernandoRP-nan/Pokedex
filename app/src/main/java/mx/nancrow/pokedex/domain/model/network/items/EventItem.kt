package mx.nancrow.pokedex.domain.model.network.items

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import mx.nancrow.pokedex.domain.model.network.response.ReviewResponse

@Parcelize
data class EventItem(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val name: String,
    val city: String,
    val rules: String,
    val map: String = "",
    val image: String = "",
    val review: ReviewResponse?
): Parcelable
