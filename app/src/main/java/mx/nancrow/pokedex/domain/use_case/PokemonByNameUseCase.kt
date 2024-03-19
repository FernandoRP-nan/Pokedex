package mx.nancrow.pokedex.domain.use_case

import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.util.Resource


interface PokemonByNameUseCase {
    //suspend operator fun invoke(artistId: Int): Resource<List<EventResponse>>
    suspend operator fun invoke(pokemonName: String): Resource<PokemonResponse>

}