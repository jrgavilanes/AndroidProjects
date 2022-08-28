package es.codekai.androidprojects.data.network.response

import com.google.gson.annotations.SerializedName
import es.codekai.androidprojects.data.network.model.RickandmortyNetworkModel

data class RickandmortyNetworkResponse(
    @SerializedName("results") val results: List<RickandmortyNetworkModel>,
)