package es.codekai.androidprojects.domain.model

import es.codekai.androidprojects.data.database.entities.ExampleEntity
import es.codekai.androidprojects.data.network.model.ExampleNetworkModel

data class ExampleDomainModel(val quote: String, val author: String)

fun ExampleNetworkModel.toDomain() = ExampleDomainModel(quote = this.quote, author = this.author)

fun ExampleEntity.toDomain() = ExampleDomainModel(quote = this.quote, author = this.author)
