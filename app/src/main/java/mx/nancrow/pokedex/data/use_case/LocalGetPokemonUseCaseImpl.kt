package mx.nancrow.pokedex.data.use_case

import kotlinx.coroutines.flow.Flow
import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity
import mx.nancrow.pokedex.domain.repository.LocalRepository
import mx.nancrow.pokedex.domain.use_case.LocalGetPokemonUseCase

class LocalGetPokemonUseCaseImpl(
    private val localRepository: LocalRepository
): LocalGetPokemonUseCase {
    override fun invoke(id: Int): Flow<FavoritePokemonEntity> {
        return localRepository.getFavoritePokemon(id)
    }
}