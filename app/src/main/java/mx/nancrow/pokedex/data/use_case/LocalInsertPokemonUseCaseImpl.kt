package mx.nancrow.pokedex.data.use_case

import mx.nancrow.pokedex.domain.use_case.LocalInsertPokemonUseCase
import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity
import mx.nancrow.pokedex.domain.repository.LocalRepository

class LocalInsertPokemonUseCaseImpl(
    private val localRepository: LocalRepository
): LocalInsertPokemonUseCase {
    override suspend fun invoke(
        favoritePokemonEntity: FavoritePokemonEntity
    ) {
        localRepository.insertUser(favoritePokemonEntity)
    }
}