package mx.nancrow.pokedex.data.use_case

import mx.nancrow.pokedex.domain.model.network.response.ListPokemonResponse
import mx.nancrow.pokedex.domain.repository.PokemonRepository
import mx.nancrow.pokedex.domain.use_case.ListAllPokemonUseCase
import mx.nancrow.pokedex.util.Resource

class ListAllPokemonUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : ListAllPokemonUseCase {

    override suspend fun invoke(
    ): Resource<ListPokemonResponse> {
        return pokemonRepository.getListAllPokemon()
    }

}