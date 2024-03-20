package mx.nancrow.pokedex.domain.use_case

import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity

interface LocalUpdatePokemonUseCase {
    suspend operator fun invoke(
        favoritePokemonEntity: FavoritePokemonEntity
    )
}