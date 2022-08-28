package es.codekai.androidprojects.data.network.model

import com.google.gson.annotations.SerializedName

data class ExampleNetworkModel(
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String,
)
