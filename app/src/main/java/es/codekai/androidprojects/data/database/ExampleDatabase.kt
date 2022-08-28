package es.codekai.androidprojects.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.codekai.androidprojects.data.database.dao.ExampleDao
import es.codekai.androidprojects.data.database.entities.ExampleEntity

@Database(entities = [ExampleEntity::class], version = 1)
abstract class ExampleDatabase : RoomDatabase() {
    abstract fun getExampleDao(): ExampleDao
}