package es.codekai.androidprojects.data

import es.codekai.androidprojects.data.database.dao.ExampleDao
import es.codekai.androidprojects.data.database.entities.ExampleEntity
import es.codekai.androidprojects.data.network.ExampleService
import es.codekai.androidprojects.domain.model.ExampleDomainModel
import es.codekai.androidprojects.domain.model.toDomain
import javax.inject.Inject

class ExampleRepository @Inject constructor(
    private val exampleService: ExampleService,
    private val exampleDao: ExampleDao
) {
    suspend fun getAllExamplesFromApi(): List<ExampleDomainModel> {
        val response = exampleService.getExamples()
        return response.map { it.toDomain() }
    }

    suspend fun getAllExamplesFromDatabase(): List<ExampleDomainModel> {
        val response = exampleDao.getAllExamples()
        return response.map { it.toDomain() }
    }

    suspend fun insertExamples(quotes: List<ExampleEntity>) {
        exampleDao.insertAll(quotes)
    }

    suspend fun emptyExamples() {
        exampleDao.emptyExamples()
    }
}
