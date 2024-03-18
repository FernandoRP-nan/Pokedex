package mx.nancrow.pokedex.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.update
import mx.nancrow.pokedex.BuildConfig
import mx.nancrow.pokedex.MainActivity.Companion._networkAvailable
import mx.nancrow.pokedex.data.remote.PokemonApiService
import mx.nancrow.pokedex.domain.preferences.Preferences
import mx.nancrow.pokedex.util.hasInternetConnection
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        appContext: Context,
        preferences: Preferences
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Authorization", "Bearer ${preferences.loadSessionToken()}")
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor { message ->
                _networkAvailable.update { hasInternetConnection(appContext) }
            }.apply {
                level= HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApiService::class.java)
    }
}