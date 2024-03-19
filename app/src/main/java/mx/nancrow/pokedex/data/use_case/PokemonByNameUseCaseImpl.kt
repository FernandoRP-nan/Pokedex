package mx.nancrow.pokedex.data.use_case

import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.repository.PokemonRepository
import mx.nancrow.pokedex.domain.use_case.PokemonByNameUseCase
import mx.nancrow.pokedex.domain.use_case.PokemonUseCase
import mx.nancrow.pokedex.util.Resource

class PokemonByNameUseCaseImpl(
    private val pokemonRepository: PokemonRepository
): PokemonByNameUseCase {

    override suspend fun invoke(pokemonName: String): Resource<PokemonResponse> {
        return pokemonRepository.getPokemonByName(pokemonName)
    }

}