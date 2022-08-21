package es.codekai.androidprojects.domain

import es.codekai.androidprojects.data.QuoteRepository
import es.codekai.androidprojects.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend operator fun invoke(): Quote? {
        val quotes = quoteRepository.getAllQuotesFromDatabase()
        if (quotes.isNotEmpty()) {
            return quotes[quotes.indices.random()]
        }
        return null
    }
}
