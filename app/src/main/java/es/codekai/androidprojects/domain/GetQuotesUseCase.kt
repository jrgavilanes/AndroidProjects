package es.codekai.androidprojects.domain

import es.codekai.androidprojects.data.QuoteRepository
import es.codekai.androidprojects.data.database.entities.toDatabase
import es.codekai.androidprojects.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): List<Quote> {
        val quotes = repository.getAllQuotesFromApi()
        return if (quotes.isNotEmpty()) {
            repository.emptyQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        } else {
            repository.getAllQuotesFromDatabase()
        }
    }
}
