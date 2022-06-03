package dev.alejo.quoteapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.alejo.quoteapp.data.model.QuoteModel
import dev.alejo.quoteapp.data.model.QuoteProvider
import dev.alejo.quoteapp.domain.GetQuotesUseCase
import kotlinx.coroutines.launch

class QuoteViewModel: ViewModel() {

    val quoteResponse = MutableLiveData<QuoteModel>()
    val isLoadingData = MutableLiveData<Boolean>()
    val getQuotesUseCase = GetQuotesUseCase()

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
        // val currentQuote = QuoteProvider.getRandomQuote()
        // quoteResponse.postValue(currentQuote)
    }

}