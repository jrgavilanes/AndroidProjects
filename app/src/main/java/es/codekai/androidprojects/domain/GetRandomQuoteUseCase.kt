package es.codekai.androidprojects.domain

import es.codekai.androidprojects.data.model.QuoteModel
import es.codekai.androidprojects.data.model.QuoteProvider
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val quoteProvider: QuoteProvider
) {
    operator fun invoke(): QuoteModel? {
        val quotes = quoteProvider.quotes
        if (quotes.size > 1) {
            return quotes[quotes.indices.random()]
        }
        return null
    }
}
