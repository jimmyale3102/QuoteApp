package dev.alejo.quoteapp.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.quoteapp.domain.GetQuotesUseCase
import dev.alejo.quoteapp.domain.GetRandomQuoteUseCase
import dev.alejo.quoteapp.domain.model.QuoteItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
): ViewModel() {

    val quoteResponse = MutableLiveData<QuoteItem>()
    val isLoadingData = MutableLiveData<Boolean>()

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

    @SuppressLint("NullSafeMutableLiveData")
    fun getRandomQuote() {
        viewModelScope.launch {
            isLoadingData.postValue(true)
            val quote = getRandomQuoteUseCase()
            if(quote != null)
                quoteResponse.postValue(quote)
            isLoadingData.postValue(false)
        }
    }

}