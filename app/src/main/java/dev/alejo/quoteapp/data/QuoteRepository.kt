package dev.alejo.quoteapp.data

import dev.alejo.quoteapp.data.model.QuoteModel
import dev.alejo.quoteapp.data.model.QuoteProvider
import dev.alejo.quoteapp.data.network.QuoteService
import javax.inject.Inject

/**
 * This class management if we are gonna access to the database or network
 */

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteProvider: QuoteProvider
) {

    suspend fun getAllQuotes(): List<QuoteModel> {
        val response = api.getQuote()
        quoteProvider.quotes = response
        return response
    }

    fun getRandomQuote(): QuoteModel? {
        val quotes = quoteProvider.quotes
        if(quotes.isNotEmpty())
            return quotes[(quotes.indices).random()]
        return null
    }

}