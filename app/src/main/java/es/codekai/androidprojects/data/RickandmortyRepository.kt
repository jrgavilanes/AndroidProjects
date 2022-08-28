package es.codekai.androidprojects.data

import es.codekai.androidprojects.data.network.RickandMortyService
import es.codekai.androidprojects.domain.model.RickandmortyDomainModel
import es.codekai.androidprojects.domain.model.toDomain
import javax.inject.Inject

class RickandmortyRepository @Inject constructor(
    private val rickandMortyService: RickandMortyService
) {
    suspend fun getAllRickandmortyCharacters(): List<RickandmortyDomainModel> {
        val response = rickandMortyService.getAllRickandmortyCharacters()
        return response.map { it.toDomain() }
    }
}
