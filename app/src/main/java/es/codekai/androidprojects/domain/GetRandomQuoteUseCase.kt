package es.codekai.androidprojects.domain

import es.codekai.androidprojects.data.QuoteRepository
import es.codekai.androidprojects.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): Quote? {
        val quotes = repository.getAllQuotesFromDatabase()
        if (quotes.size > 1) {
            return quotes[quotes.indices.random()]
        }
        return null
    }
}
