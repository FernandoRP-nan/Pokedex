package mx.nancrow.pokedex.data.use_case

import mx.nancrow.pokedex.domain.model.network.response.ListPokemonResponse
import mx.nancrow.pokedex.domain.repository.PokemonRepository
import mx.nancrow.pokedex.domain.use_case.ListPokemonUseCase
import mx.nancrow.pokedex.util.Resource

class ListPokemonUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : ListPokemonUseCase {

    override suspend fun invoke(
        limit: Int, offset: Int
    ): Resource<ListPokemonResponse> {
        return pokemonRepository.getListPokemon(limit, offset)
    }

}