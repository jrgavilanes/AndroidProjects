package es.codekai.androidprojects.domain

import es.codekai.androidprojects.data.ExampleRepository
import es.codekai.androidprojects.data.database.entities.toDatabase
import es.codekai.androidprojects.domain.model.ExampleDomainModel
import javax.inject.Inject

class ExampleUseCase @Inject constructor(
    private val exampleRepository: ExampleRepository
) {
    suspend fun getAllExamples(): List<ExampleDomainModel> {
        val examples = exampleRepository.getAllExamplesFromApi()
        return if (examples.isNotEmpty()) {
            exampleRepository.emptyExamples()
            exampleRepository.insertExamples(examples.map { it.toDatabase() })
            examples
        } else {
            exampleRepository.getAllExamplesFromDatabase()
        }
    }

    suspend fun getRandomExample(): ExampleDomainModel? {
        val examples = exampleRepository.getAllExamplesFromDatabase()
        if (examples.isNotEmpty()) {
            return examples[examples.indices.random()]
        }
        return null
    }
}
