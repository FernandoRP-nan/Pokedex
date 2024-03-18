package mx.nancrow.pokedex.domain.use_case

import mx.nancrow.pokedex.domain.model.network.response.PokemonSpeciesResponse
import mx.nancrow.pokedex.util.Resource


interface PokemonSpeciesUseCase {
    //suspend operator fun invoke(artistId: Int): Resource<List<EventResponse>>
    suspend operator fun invoke(pokemonId: Int): Resource<PokemonSpeciesResponse>

}