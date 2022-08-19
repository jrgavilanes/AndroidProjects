package es.codekai.androidprojects.data

import es.codekai.androidprojects.data.model.QuoteModel
import es.codekai.androidprojects.data.model.QuoteProvider
import es.codekai.androidprojects.data.network.QuoteService

class QuoteRepository {
    private val api = QuoteService()
    suspend fun getAllQuotes(): List<QuoteModel> {
        val response = api.getQuotes()
        QuoteProvider.quotes = response // Local cache
        return response
    }
}
