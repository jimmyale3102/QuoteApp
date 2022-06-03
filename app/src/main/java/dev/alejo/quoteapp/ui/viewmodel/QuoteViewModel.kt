package dev.alejo.quoteapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.alejo.quoteapp.data.model.QuoteModel
import dev.alejo.quoteapp.domain.GetQuotesUseCase
import dev.alejo.quoteapp.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

class QuoteViewModel: ViewModel() {

    val quoteResponse = MutableLiveData<QuoteModel>()
    val isLoadingData = MutableLiveData<Boolean>()
    val getQuotesUseCase = GetQuotesUseCase()
    val getRandomQuoteUseCase = GetRandomQuoteUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoadingData.postValue(true)
            val result = getQuotesUseCase()
            if(!result.isNullOrEmpty()) {
                quoteResponse.postValue(result[0])
                isLoadingData.postValue(false)
            }
        }
    }

    fun getRandomQuote() {
        isLoadingData.postValue(true)
        val quote = getRandomQuoteUseCase()
        if(quote != null)
            quoteResponse.postValue(quote)
        isLoadingData.postValue(false)
    }

}