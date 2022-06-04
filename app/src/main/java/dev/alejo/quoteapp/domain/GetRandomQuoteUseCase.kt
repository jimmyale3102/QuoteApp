package dev.alejo.quoteapp.domain

import dev.alejo.quoteapp.data.QuoteRepository
import dev.alejo.quoteapp.data.model.QuoteModel
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
) {

    operator fun invoke(): QuoteModel? = repository.getRandomQuote()

}