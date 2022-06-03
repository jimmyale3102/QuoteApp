package dev.alejo.quoteapp.data

import dev.alejo.quoteapp.data.model.QuoteModel
import dev.alejo.quoteapp.data.model.QuoteProvider
import dev.alejo.quoteapp.data.network.QuoteService

/**
 * This class management if we are gonna access to the database or network
 */

class QuoteRepository {

    private val api = QuoteService()

    suspend fun getAllQuotes(): List<QuoteModel> {
        val response = api.getQuote()
        QuoteProvider.quotes = response
        return response
    }

    fun getRandomQuote(): QuoteModel? {
        val quotes = QuoteProvider.quotes
        if(!quotes.isNullOrEmpty())
            return quotes[(quotes.indices).random()]
        return null
    }

}