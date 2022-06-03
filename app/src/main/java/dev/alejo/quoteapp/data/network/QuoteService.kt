package dev.alejo.quoteapp.data.network

import dev.alejo.quoteapp.core.RetrofitHelper
import dev.alejo.quoteapp.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuote(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(QuoteApiClient::class.java)
                .getAllQuotes()
            response.body() ?: emptyList()
        }
    }

}