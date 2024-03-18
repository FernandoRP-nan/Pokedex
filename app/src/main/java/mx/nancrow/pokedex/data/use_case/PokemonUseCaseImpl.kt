package mx.nancrow.pokedex.data.use_case

import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.repository.PokemonRepository
import mx.nancrow.pokedex.domain.use_case.PokemonUseCase
import mx.nancrow.pokedex.util.Resource

class PokemonUseCaseImpl(
    private val pokemonRepository: PokemonRepository
): PokemonUseCase {

    override suspend fun invoke(pokemonId: Int): Resource<PokemonResponse> {
        return pokemonRepository.getPokemon(pokemonId)
    }

}