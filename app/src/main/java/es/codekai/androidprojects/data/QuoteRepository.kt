package es.codekai.androidprojects.data

import es.codekai.androidprojects.data.model.QuoteModel
import es.codekai.androidprojects.data.model.QuoteProvider
import es.codekai.androidprojects.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteProvider: QuoteProvider
) {
    suspend fun getAllQuotes(): List<QuoteModel> {
        val response = api.getQuotes()
        quoteProvider.quotes = response // Local cache
        return response
    }
}
