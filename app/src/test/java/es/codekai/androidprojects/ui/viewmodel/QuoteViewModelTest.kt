package es.codekai.androidprojects.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import es.codekai.androidprojects.domain.GetQuotesUseCase
import es.codekai.androidprojects.domain.GetRandomQuoteUseCase
import es.codekai.androidprojects.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelTest {

    @RelaxedMockK
    lateinit var getQuotesUseCase: GetQuotesUseCase

    @RelaxedMockK
    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onCreate() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined) // Para Poder mockear el viewModelScope
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created gets all quotes from the API and shows the first one`() =
        runTest {
            // Given
            val quotes = listOf(
                Quote("quote1", "author1"),
                Quote("quote2", "author2"),
            )
            coEvery { getQuotesUseCase() } returns quotes
            // When
            quoteViewModel.onCreate()
            // Then
            assert(quoteViewModel.quoteModel.value == quotes.first())
        }

    @Test
    fun `when asking for a random note and one is returned then the livedata is updated`() =
        runTest {
            // Given
            val quote = Quote("quote1", "author1")
            coEvery { getRandomQuoteUseCase() } returns quote
            // When
            quoteViewModel.randomQuote()
            // Then
            assert(quoteViewModel.quoteModel.value == quote)
        }

    @Test
    fun `when asking for a random note and null is returned then the livedata is not updated`() =
        runTest {
            // Given
            val quote = Quote("quote1", "author1")
            coEvery { getRandomQuoteUseCase() } returns null
            // When
            quoteViewModel.quoteModel.value = quote
            quoteViewModel.randomQuote()
            // Then
            coVerify(exactly = 1) { getRandomQuoteUseCase() }
            assert(quoteViewModel.quoteModel.value == quote)
        }
}
