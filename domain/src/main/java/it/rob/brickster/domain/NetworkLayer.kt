package it.rob.brickster.domain

import io.ktor.client.*
import io.ktor.client.request.*

class NetworkLayer(private val client: HttpClient) {

    suspend fun searchSet(query: String) : LegoResponse =
        client.get(SEARCH_TEXT_ENDPOINT) {
            parameter("prefixText", query)
        }

    companion object {
        private const val LEGO_ENDPOINT = "https://www.lego.com/"
        private const val SEARCH_TEXT_ENDPOINT = "$LEGO_ENDPOINT/service/biservice/search"
    }
}