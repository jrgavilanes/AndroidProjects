package es.codekai.androidprojects.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.codekai.androidprojects.data.database.entities.ExampleEntity

@Dao
interface ExampleDao {
    @Query("SELECT * FROM example_table ORDER BY id DESC")
    suspend fun getAllExamples(): List<ExampleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<ExampleEntity>)

    @Query("DELETE FROM example_table")
    suspend fun emptyExamples()
}