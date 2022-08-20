package es.codekai.androidprojects.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.codekai.androidprojects.data.database.dao.QuoteDao
import es.codekai.androidprojects.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao
}
