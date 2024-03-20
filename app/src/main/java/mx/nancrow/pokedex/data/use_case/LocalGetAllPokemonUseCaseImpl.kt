package mx.nancrow.pokedex.data.use_case

import kotlinx.coroutines.flow.Flow
import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity
import mx.nancrow.pokedex.domain.repository.LocalRepository
import mx.nancrow.pokedex.domain.use_case.LocalGetAllPokemonUseCase

class LocalGetAllPokemonUseCaseImpl(
    private val localRepository: LocalRepository
): LocalGetAllPokemonUseCase {
    override fun invoke(): Flow<List<FavoritePokemonEntity>> {
        return localRepository.getAllFavoritePokemon()
    }
}