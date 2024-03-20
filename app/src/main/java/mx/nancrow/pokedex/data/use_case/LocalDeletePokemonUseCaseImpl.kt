package mx.nancrow.pokedex.data.use_case

import mx.nancrow.pokedex.domain.use_case.LocalDeletePokemonUseCase
import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity
import mx.nancrow.pokedex.domain.repository.LocalRepository

class LocalDeletePokemonUseCaseImpl(
    private val localRepository: LocalRepository
): LocalDeletePokemonUseCase {
    override suspend fun invoke(
        favoritePokemonEntity: FavoritePokemonEntity
    ) {
        localRepository.deleteUser(favoritePokemonEntity)
    }
}