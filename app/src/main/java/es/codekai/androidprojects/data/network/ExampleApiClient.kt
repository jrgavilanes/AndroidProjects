package es.codekai.androidprojects.data.network

import es.codekai.androidprojects.data.network.model.ExampleNetworkModel
import retrofit2.Response
import retrofit2.http.GET

interface ExampleApiClient {
    @GET("/.json")
    suspend fun getAllExample(): Response<List<ExampleNetworkModel>>
}
