package es.codekai.androidprojects.data.network

import es.codekai.androidprojects.data.model.QuoteModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(
    private val api: QuoteApiClient
) {
    suspend fun getQuotes(dispatcher: CoroutineDispatcher = Dispatchers.IO): List<QuoteModel> {
        return withContext(dispatcher) {
            val response = api.getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}
