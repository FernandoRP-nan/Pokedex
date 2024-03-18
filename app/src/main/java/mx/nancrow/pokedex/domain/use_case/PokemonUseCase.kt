package mx.nancrow.pokedex.domain.use_case

import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.util.Resource


interface PokemonUseCase {
    //suspend operator fun invoke(artistId: Int): Resource<List<EventResponse>>
    suspend operator fun invoke(pokemonId: Int): Resource<PokemonResponse>

}