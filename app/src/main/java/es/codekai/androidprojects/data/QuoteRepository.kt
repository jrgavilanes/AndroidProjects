package es.codekai.androidprojects.data

import es.codekai.androidprojects.data.database.dao.QuoteDao
import es.codekai.androidprojects.data.database.entities.QuoteEntity
import es.codekai.androidprojects.data.database.entities.toDatabase
import es.codekai.androidprojects.data.model.QuoteModel
import es.codekai.androidprojects.data.network.QuoteService
import es.codekai.androidprojects.domain.model.Quote
import es.codekai.androidprojects.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {
    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote> {
        val response = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) {
        quoteDao.insertAll(quotes)
    }

    suspend fun emptyQuotes() {
        quoteDao.emptyQuotes()
    }
}
