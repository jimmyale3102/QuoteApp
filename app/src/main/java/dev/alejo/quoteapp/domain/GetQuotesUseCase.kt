package dev.alejo.quoteapp.domain

import dev.alejo.quoteapp.data.QuoteRepository
import dev.alejo.quoteapp.data.model.QuoteModel

class GetQuotesUseCase {

    private val repository = QuoteRepository()

    suspend operator fun invoke(): List<QuoteModel>? = repository.getAllQuotes()

}