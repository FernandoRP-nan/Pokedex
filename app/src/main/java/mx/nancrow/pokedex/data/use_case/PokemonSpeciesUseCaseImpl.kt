package mx.nancrow.pokedex.data.use_case

import mx.nancrow.pokedex.domain.model.network.response.PokemonSpeciesResponse
import mx.nancrow.pokedex.domain.repository.PokemonRepository
import mx.nancrow.pokedex.domain.use_case.PokemonSpeciesUseCase
import mx.nancrow.pokedex.util.Resource

class PokemonSpeciesUseCaseImpl(
    private val pokemonRepository: PokemonRepository
): PokemonSpeciesUseCase {

    override suspend fun invoke(pokemonId: Int): Resource<PokemonSpeciesResponse> {
        return pokemonRepository.getPokemonSpecies(pokemonId)
    }

}