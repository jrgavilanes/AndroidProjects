package es.codekai.androidprojects.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.codekai.androidprojects.data.database.ExampleDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME = "example_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ExampleDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideExampleDao(db: ExampleDatabase) = db.getExampleDao()
}
