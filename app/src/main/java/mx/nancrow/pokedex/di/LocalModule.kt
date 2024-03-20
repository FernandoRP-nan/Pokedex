package mx.com.satoritech.festlovers.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.nancrow.pokedex.data.local.PokemonDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideFestLoversDatabase(
        app: Application
    ): PokemonDatabase {
        return Room.databaseBuilder(
            app,
            PokemonDatabase::class.java,
            "fest_lovers_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}