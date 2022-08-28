package es.codekai.androidprojects.data.network

import es.codekai.androidprojects.data.network.model.RickandmortyNetworkModel
import es.codekai.androidprojects.data.network.response.RickandmortyNetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickandmortyApiClient {
    @GET("/api/character")
    suspend fun getAllRickandmortyCharacters(): Response<RickandmortyNetworkResponse>
}
