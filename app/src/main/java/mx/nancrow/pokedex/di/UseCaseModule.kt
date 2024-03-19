package mx.nancrow.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import mx.nancrow.pokedex.data.use_case.ListAllPokemonUseCaseImpl
import mx.nancrow.pokedex.data.use_case.ListPokemonUseCaseImpl
import mx.nancrow.pokedex.data.use_case.PokemonByNameUseCaseImpl
import mx.nancrow.pokedex.data.use_case.PokemonSearchUseCaseImpl
import mx.nancrow.pokedex.data.use_case.PokemonSpeciesUseCaseImpl
import mx.nancrow.pokedex.data.use_case.PokemonUseCaseImpl
import mx.nancrow.pokedex.domain.repository.PokemonRepository
import mx.nancrow.pokedex.domain.use_case.ListAllPokemonUseCase
import mx.nancrow.pokedex.domain.use_case.ListPokemonUseCase
import mx.nancrow.pokedex.domain.use_case.PokemonByNameUseCase
import mx.nancrow.pokedex.domain.use_case.PokemonSearchUseCase
import mx.nancrow.pokedex.domain.use_case.PokemonSpeciesUseCase
import mx.nancrow.pokedex.domain.use_case.PokemonUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun providePokemonUseCase(
        pokemonRepository: PokemonRepository
    ): PokemonUseCase {
        return PokemonUseCaseImpl(
            pokemonRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun providePokemonSearchUseCase(
        pokemonRepository: PokemonRepository
    ): PokemonSearchUseCase {
        return PokemonSearchUseCaseImpl(
            pokemonRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun providePokemonByNameUseCase(
        pokemonRepository: PokemonRepository
    ): PokemonByNameUseCase {
        return PokemonByNameUseCaseImpl(
            pokemonRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun providePokemonSpeciesUseCase(
        pokemonRepository: PokemonRepository
    ): PokemonSpeciesUseCase {
        return PokemonSpeciesUseCaseImpl(
            pokemonRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideListPokemonUseCase(
        pokemonRepository: PokemonRepository
    ): ListPokemonUseCase {
        return ListPokemonUseCaseImpl(
            pokemonRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideListAllPokemonUseCase(
        pokemonRepository: PokemonRepository
    ): ListAllPokemonUseCase {
        return ListAllPokemonUseCaseImpl(
            pokemonRepository
        )
    }
}