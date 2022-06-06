package dev.alejo.quoteapp.data

import dev.alejo.quoteapp.core.extensions.toDomain
import dev.alejo.quoteapp.data.database.dao.QuoteDao
import dev.alejo.quoteapp.data.database.entities.QuoteEntity
import dev.alejo.quoteapp.data.network.QuoteService
import dev.alejo.quoteapp.domain.model.QuoteItem
import javax.inject.Inject

/**
 * This class management if we are gonna access to the database or network
 */

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi(): List<QuoteItem> {
        return api.getQuote().map { quote -> quote.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<QuoteItem> {
        return quoteDao.getAllQuotes().map { quote -> quote.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) {
        quoteDao.insertAllQuotes(quotes)
    }

    suspend fun deleteAllQuotes() {
        quoteDao.deleteAllQuotes()
    }

}