package mx.nancrow.pokedex.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewResponse(
    val id: Int,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("review")
    val review: String,
    @SerializedName("date")
    val date: String
): Parcelable
