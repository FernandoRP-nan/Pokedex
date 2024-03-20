package mx.nancrow.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.nancrow.pokedex.data.local.PokemonDatabase
import mx.nancrow.pokedex.data.remote.PokemonApiService
import mx.nancrow.pokedex.data.repository.LocalRepositoryImpl
import mx.nancrow.pokedex.data.repository.PokemonRepositoryImpl
import mx.nancrow.pokedex.domain.preferences.Preferences
import mx.nancrow.pokedex.domain.repository.LocalRepository
import mx.nancrow.pokedex.domain.repository.PokemonRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideEventRepository(
        api: PokemonApiService,

    ): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(
        database: PokemonDatabase
    ): LocalRepository {
        return LocalRepositoryImpl(
            database.pokemonFavoriteDao()
        )
    }
}