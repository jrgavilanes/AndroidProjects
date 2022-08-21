package es.codekai.androidprojects.domain

import es.codekai.androidprojects.data.QuoteRepository
import es.codekai.androidprojects.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest {

    @RelaxedMockK
    lateinit var quoteRepository: QuoteRepository

    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun `get all quotes of database a return a random one`() = runBlocking {
        // Given
        val mockedQuote = Quote("message", "me")

        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns listOf(mockedQuote)
        // When
        val result = getRandomQuoteUseCase()
        // Then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
        assert(result == mockedQuote)
    }
}
