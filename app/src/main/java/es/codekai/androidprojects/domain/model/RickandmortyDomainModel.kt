package es.codekai.androidprojects.domain.model

import es.codekai.androidprojects.data.network.model.RickandmortyNetworkModel

data class RickandmortyDomainModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val url: String,
    val created: String,
)

fun RickandmortyNetworkModel.toDomain() = RickandmortyDomainModel(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    image = image,
    url = url,
    created = created
)
