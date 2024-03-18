package mx.nancrow.pokedex.domain.model.network.response

import com.google.gson.annotations.SerializedName

data class BaseResponse <T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T
)
