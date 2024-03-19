package mx.nancrow.pokedex.domain.use_case

import mx.nancrow.pokedex.domain.model.network.response.ListPokemonResponse
import mx.nancrow.pokedex.util.Resource


interface ListPokemonUseCase {
    //suspend operator fun invoke(artistId: Int): Resource<List<EventResponse>>
    suspend operator fun invoke(
        limit: Int, offset: Int
    ): Resource<ListPokemonResponse>

}