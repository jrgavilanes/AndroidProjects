package es.codekai.androidprojects.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.codekai.androidprojects.data.database.QuoteDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME = "quotes_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, QuoteDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQuoteDao(db: QuoteDatabase) = db.getQuoteDao()
}
