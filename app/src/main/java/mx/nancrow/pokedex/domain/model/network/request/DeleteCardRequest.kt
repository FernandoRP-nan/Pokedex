package mx.nancrow.pokedex.domain.model.network.request

import com.google.gson.annotations.SerializedName

data class DeleteCardRequest(
    @SerializedName("card_id") var cardId : Int
)
