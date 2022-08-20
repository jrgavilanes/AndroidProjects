package es.codekai.androidprojects.domain.model

import es.codekai.androidprojects.data.database.entities.QuoteEntity
import es.codekai.androidprojects.data.model.QuoteModel

data class Quote(val quote: String, val author: String)

fun QuoteModel.toDomain() = Quote(quote = this.quote, author = this.author)

fun QuoteEntity.toDomain() = Quote(quote = this.quote, author = this.author)
