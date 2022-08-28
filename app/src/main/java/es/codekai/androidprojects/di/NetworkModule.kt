package es.codekai.androidprojects.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.codekai.androidprojects.data.network.ExampleApiClient
import es.codekai.androidprojects.data.network.RickandmortyApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
//            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/.json/")
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideExampleApiClient(retrofit: Retrofit): ExampleApiClient {
        return retrofit.create(ExampleApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRickandMortyApiClient(retrofit: Retrofit): RickandmortyApiClient {
        return retrofit.create(RickandmortyApiClient::class.java)
    }
}
