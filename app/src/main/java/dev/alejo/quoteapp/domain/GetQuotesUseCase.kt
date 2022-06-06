package dev.alejo.quoteapp.domain

import dev.alejo.quoteapp.core.extensions.toDatabase
import dev.alejo.quoteapp.data.QuoteRepository
import dev.alejo.quoteapp.domain.model.QuoteItem
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): List<QuoteItem> {
        val quotes = repository.getAllQuotesFromApi()
        return if(quotes.isNotEmpty()) {
            repository.deleteAllQuotes()
            repository.insertQuotes(quotes.map { quote -> quote.toDatabase() })
            quotes
        } else {
            repository.getAllQuotesFromDatabase()
        }
    }
}