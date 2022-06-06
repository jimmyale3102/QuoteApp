package dev.alejo.quoteapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.alejo.quoteapp.domain.GetQuotesUseCase
import dev.alejo.quoteapp.domain.GetRandomQuoteUseCase
import dev.alejo.quoteapp.domain.model.QuoteItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class ExampleUnitTest {

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase
    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase
    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all quotes and set the first value`() = runTest {
        // Given
        val quotes = listOf(
            QuoteItem("quote1", "author1"),
            QuoteItem("quote2", "author2"),
            QuoteItem("quote3", "author3")
        )
        coEvery { getQuotesUseCase() } returns quotes

        // When
        val result = quoteViewModel.onCreate()

        // Then
        assert(quoteViewModel.quoteResponse.value == quotes.first())
    }

    @Test
    fun `when randomQuoteUseCase returns a quote set on the livedata` () = runTest {
        // Given
        val quote = QuoteItem("quote1", "author1")
        coEvery { getRandomQuoteUseCase() } returns quote

        // When
        quoteViewModel.getRandomQuote()

        // Then
        assert(quoteViewModel.quoteResponse.value == quote)
    }

    @Test
    fun `if randomQuoteUseCase returns null keep the last value` () = runTest {
        // Given
        val quote = QuoteItem("quote1", "author1")
        quoteViewModel.quoteResponse.value = quote
        coEvery { getRandomQuoteUseCase() } returns null

        // When
        quoteViewModel.getRandomQuote()

        // Then
        assert(quoteViewModel.quoteResponse.value == quote)
    }

}