package es.codekai.androidprojects.domain

import es.codekai.androidprojects.data.ExampleRepository
import es.codekai.androidprojects.domain.model.ExampleDomainModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ExampleUseCaseTest {
    @RelaxedMockK
    private lateinit var exampleRepository: ExampleRepository

    private lateinit var exampleUseCase: ExampleUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        exampleUseCase = ExampleUseCase(exampleRepository)
    }

    @Test
    fun `when API returns no quotes, then I show stored quotes in database`() = runBlocking {
        // Given
        coEvery { exampleRepository.getAllExamplesFromApi() } returns emptyList()
        // When
        exampleUseCase.getAllExamples()
        // Then
        coVerify(exactly = 1) { exampleRepository.getAllExamplesFromDatabase() }
    }

    @Test
    fun `when API returns quotes, then I update database quotes and return the new ones`() =
        runBlocking {
            // Given
            val mockedResult = listOf(ExampleDomainModel(author = "yo", quote = "message"))
            coEvery { exampleRepository.getAllExamplesFromApi() } returns mockedResult
            // When
            val result = exampleUseCase.getAllExamples()
            // Then
            coVerify(exactly = 1) { exampleRepository.emptyExamples() }
            coVerify(exactly = 1) { exampleRepository.insertExamples(any()) }
            coVerify(exactly = 0) { exampleRepository.getAllExamplesFromDatabase() }
            assert(result == mockedResult)
        }

    @Test
    fun `get all quotes of database a return a random one`() = runBlocking {
        // Given
        val mockedQuote = ExampleDomainModel("message", "me")

        coEvery { exampleRepository.getAllExamplesFromDatabase() } returns listOf(mockedQuote)
        // When
        val result = exampleUseCase.getRandomExample()
        // Then
        coVerify(exactly = 1) { exampleRepository.getAllExamplesFromDatabase() }
        assert(result == mockedQuote)
    }
}
