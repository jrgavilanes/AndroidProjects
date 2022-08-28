package es.codekai.androidprojects.domain

import es.codekai.androidprojects.data.RickandmortyRepository
import es.codekai.androidprojects.domain.model.RickandmortyDomainModel
import javax.inject.Inject

class RickandmortyUseCase @Inject constructor(
    private val rickandmortyRepository: RickandmortyRepository
) {
    suspend fun getAllRickandmortyCharacters(): List<RickandmortyDomainModel> {
        val characters = rickandmortyRepository.getAllRickandmortyCharacters()
        return characters.ifEmpty {
            emptyList()
        }
    }
}
