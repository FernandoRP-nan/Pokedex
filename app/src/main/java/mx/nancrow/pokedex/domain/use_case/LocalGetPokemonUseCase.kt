package mx.nancrow.pokedex.domain.use_case

import kotlinx.coroutines.flow.Flow
import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity

interface LocalGetPokemonUseCase {
    operator fun invoke(id: Int): Flow<FavoritePokemonEntity>
}