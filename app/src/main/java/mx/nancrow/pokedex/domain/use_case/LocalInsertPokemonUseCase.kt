package mx.nancrow.pokedex.domain.use_case

import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity

interface LocalInsertPokemonUseCase {
    suspend operator fun invoke(
        favoritePokemonEntity: FavoritePokemonEntity
    )
}