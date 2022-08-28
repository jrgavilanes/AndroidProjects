package es.codekai.androidprojects.data.network

import es.codekai.androidprojects.data.network.model.ExampleNetworkModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExampleService @Inject constructor(
    private val exampleApiClient: ExampleApiClient
) {
    suspend fun getExamples(dispatcher: CoroutineDispatcher = Dispatchers.IO): List<ExampleNetworkModel> {
        return withContext(dispatcher) {
            val response = exampleApiClient.getAllExample()
            response.body() ?: emptyList()
        }
    }
}
