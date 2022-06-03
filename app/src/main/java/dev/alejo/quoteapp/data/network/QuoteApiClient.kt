package dev.alejo.quoteapp.data.network

import dev.alejo.quoteapp.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {

    @GET(".json")
    suspend fun getAllQuotes(): Response<List<QuoteModel>>

}