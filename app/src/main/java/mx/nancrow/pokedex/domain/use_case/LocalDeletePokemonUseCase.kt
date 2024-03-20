package mx.nancrow.pokedex.domain.use_case

import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity


interface LocalDeletePokemonUseCase {
    suspend operator fun invoke(
        favoritePokemonEntity: FavoritePokemonEntity
    )
}