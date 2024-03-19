package mx.nancrow.pokedex.data.use_case

import mx.nancrow.pokedex.domain.model.network.response.PokemonSearchResponse
import mx.nancrow.pokedex.domain.repository.PokemonRepository
import mx.nancrow.pokedex.domain.use_case.PokemonSearchUseCase
import mx.nancrow.pokedex.util.Resource

class PokemonSearchUseCaseImpl(
    private val pokemonRepository: PokemonRepository
): PokemonSearchUseCase {

    override suspend fun invoke(pokemonName: String): Resource<PokemonSearchResponse> {
        return pokemonRepository.getPokemonSearch(pokemonName)
    }

}