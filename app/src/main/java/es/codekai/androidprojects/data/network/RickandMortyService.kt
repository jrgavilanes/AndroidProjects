package es.codekai.androidprojects.data.network

import android.util.Log
import es.codekai.androidprojects.data.network.model.RickandmortyNetworkModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RickandMortyService @Inject constructor(
    private val rickandmortyApiClient: RickandmortyApiClient
) {
    suspend fun getAllRickandmortyCharacters(dispatcher: CoroutineDispatcher = Dispatchers.IO): List<RickandmortyNetworkModel> {
        return withContext(dispatcher) {
            val response = rickandmortyApiClient.getAllRickandmortyCharacters()
            if (!response.isSuccessful) {
                Log.d(
                    "juanra",
                    "Error ${response.code()}, ${
                    response.errorBody()
                    }, ${response.body()}, ${response.message()}"
                )
            }
            response.body()?.results ?: emptyList()
        }
    }
}
