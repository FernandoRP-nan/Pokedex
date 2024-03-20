package mx.nancrow.pokedex.domain.use_case

import kotlinx.coroutines.flow.Flow
import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity

interface LocalGetAllPokemonUseCase {
    operator fun invoke(): Flow<List<FavoritePokemonEntity>>
}