package mx.nancrow.pokedex.data.use_case

import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity
import mx.nancrow.pokedex.domain.repository.LocalRepository
import mx.nancrow.pokedex.domain.use_case.LocalUpdatePokemonUseCase

class LocalUpdatePokemonUseCaseImpl(
    private val localRepository: LocalRepository
): LocalUpdatePokemonUseCase {
    override suspend fun invoke(
        favoritePokemonEntity: FavoritePokemonEntity
    ) {
        localRepository.updatePokemon(favoritePokemonEntity)
    }
}