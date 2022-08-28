package es.codekai.androidprojects.presentation.example

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import es.codekai.androidprojects.domain.ExampleUseCase
import es.codekai.androidprojects.domain.model.ExampleDomainModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
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
class ExampleViewModelTest {
    @RelaxedMockK
    lateinit var exampleUseCase: ExampleUseCase

    private lateinit var exampleViewModel: ExampleViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onCreate() {
        MockKAnnotations.init(this)
        exampleViewModel = ExampleViewModel(exampleUseCase)
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
                ExampleDomainModel("quote1", "author1"),
                ExampleDomainModel("quote2", "author2"),
            )
            coEvery { exampleUseCase.getAllExamples() } returns quotes
            // When
            exampleViewModel.onCreate()
            // Then
            assert(exampleViewModel.example.value == quotes.first())
        }

    @Test
    fun `when asking for a random note and null is returned then the livedata is not updated`() =
        runTest {
            // Given
            val quote = ExampleDomainModel("quote1", "author1")
            coEvery { exampleUseCase.getRandomExample() } returns quote
            // When
            exampleViewModel.getRandomQuote()
            // Then
            assert(exampleViewModel.example.value == quote)

            // Given
            coEvery { exampleUseCase.getRandomExample() } returns null
            // When
            exampleViewModel.getRandomQuote()
            // Then
            assert(exampleViewModel.example.value == quote)
        }
}
