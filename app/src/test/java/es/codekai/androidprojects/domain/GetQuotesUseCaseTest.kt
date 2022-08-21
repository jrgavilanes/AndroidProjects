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

class GetQuotesUseCaseTest {
    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }

    @Test
    fun `when API returns no quotes, then I show stored quotes in database`() = runBlocking {
        // Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()
        // When
        getQuotesUseCase()
        // Then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
    }

    @Test
    fun `when API returns quotes, then I update database quotes and return the new ones`() =
        runBlocking {
            // Given
            val mockedResult = listOf(Quote(author = "yo", quote = "message"))
            coEvery { quoteRepository.getAllQuotesFromApi() } returns mockedResult
            // When
            val result = getQuotesUseCase()
            // Then
            coVerify(exactly = 1) { quoteRepository.emptyQuotes() }
            coVerify(exactly = 1) { quoteRepository.insertQuotes(any()) }
            coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDatabase() }
            assert(result == mockedResult)
        }
}
