package dev.alejo.quoteapp.domain

import dev.alejo.quoteapp.data.QuoteRepository
import dev.alejo.quoteapp.domain.model.QuoteItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetQuotesUseCaseTest {

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository
    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }

    @Test
    fun `when the api does not return anything then get values from database`() = runBlocking {
        // Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()
        // When
        getQuotesUseCase()
        // Then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
    }

    @Test
    fun `when the api returns something then get values from api`() = runBlocking {
        // Given
        val myList = listOf(
            QuoteItem("First", "Me"),
            QuoteItem("Second", "Aristi")
        )
        coEvery { quoteRepository.getAllQuotesFromApi() } returns myList
        // When
        val response = getQuotesUseCase()
        // Then
        coVerify(exactly = 1) { quoteRepository.deleteAllQuotes() }
        coVerify(exactly = 1) { quoteRepository.insertQuotes(any()) }
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDatabase() }
        assert(myList == response)
    }
}