package es.codekai.androidprojects.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.codekai.androidprojects.domain.model.ExampleDomainModel

@Entity(tableName = "example_table")
data class ExampleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,

    @ColumnInfo(name = "quote") val quote: String,

    @ColumnInfo(name = "author") val author: String
)

fun ExampleDomainModel.toDatabase() = ExampleEntity(quote = this.quote, author = this.author)
