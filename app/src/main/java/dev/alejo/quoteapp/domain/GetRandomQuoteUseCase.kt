package dev.alejo.quoteapp.domain

import dev.alejo.quoteapp.data.QuoteRepository
import dev.alejo.quoteapp.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): Quote? {
        val quotes = repository.getAllQuotesFromDatabase()
        if(quotes.isNotEmpty())
            return quotes[(quotes.indices).random()]
        return null
    }
}