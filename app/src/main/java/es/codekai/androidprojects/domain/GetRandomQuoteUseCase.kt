package es.codekai.androidprojects.domain

import es.codekai.androidprojects.data.model.QuoteModel
import es.codekai.androidprojects.data.model.QuoteProvider

class GetRandomQuoteUseCase {
    operator fun invoke(): QuoteModel? {
        if (QuoteProvider.quotes.size > 1) {
            return QuoteProvider.quotes[QuoteProvider.quotes.indices.random()]
        }
        return null
    }
}
