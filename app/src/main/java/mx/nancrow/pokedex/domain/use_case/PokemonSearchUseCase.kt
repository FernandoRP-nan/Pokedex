package mx.nancrow.pokedex.domain.use_case

import mx.nancrow.pokedex.domain.model.network.response.PokemonSearchResponse
import mx.nancrow.pokedex.util.Resource


interface PokemonSearchUseCase {
    //suspend operator fun invoke(artistId: Int): Resource<List<EventResponse>>
    suspend operator fun invoke(pokemonName: String): Resource<PokemonSearchResponse>

}