package mx.com.satoritech.festlovers.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.nancrow.pokedex.data.local.FestLoversDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideFestLoversDatabase(
        app: Application
    ): FestLoversDatabase {
        return Room.databaseBuilder(
            app,
            FestLoversDatabase::class.java,
            "fest_lovers_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}