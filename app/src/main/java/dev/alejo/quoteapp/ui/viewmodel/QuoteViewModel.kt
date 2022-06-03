package dev.alejo.quoteapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.alejo.quoteapp.data.model.QuoteModel
import dev.alejo.quoteapp.data.model.QuoteProvider

class QuoteViewModel: ViewModel() {

    val quoteResponse = MutableLiveData<QuoteModel>()

    fun getRandomQuote() {
        val currentQuote = QuoteProvider.getRandomQuote()
        quoteResponse.postValue(currentQuote)
    }

}