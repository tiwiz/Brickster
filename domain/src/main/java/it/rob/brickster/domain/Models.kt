package it.rob.brickster.domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class LegoSetDTO(
    @SerialName("set_num") val setNum: String,
    @SerialName("name") val name: String,
    @SerialName("year") val year: Int,
    @SerialName("theme_id") val themeId: Int,
    @SerialName("num_parts") val numParts: Int,
    @SerialName("set_img_url") val imageUrl: String?,
    @SerialName("set_url") val setUrl: String,
    @SerialName("last_modified_dt") val lastModifiedAt: String?
)

@Serializable
data class ResponseDTO(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<LegoSetDTO>
)

/**
 * LEGO Service
 * */

@Serializable
data class Instructions(
    @SerialName("description") val description: String,
    @SerialName("pdfLocation") val pdfUrl: String,
    @SerialName("downloadSize") val downloadSize: String,
    @SerialName("frontpageInfo") val instructionsCover: String,
    @SerialName("oNum") val bookNumber: Int,
    @SerialName("isAlternative") val isAlternative: Boolean
)

@Serializable
data class Product(
    @SerialName("productId") val setId: String,
    @SerialName("productName") val setName: String,
    @SerialName("productImage") val setImage: String,
    @SerialName("themeName") val themeName: String,
    @SerialName("launchYear") val launchYear: Int,
    @SerialName("buildingInstructions") val instructions: List<Instructions>
)

@Serializable
data class LegoResponse(
    @SerialName("count") val count: Int,
    @SerialName("totalCount") val totalCount: Int,
    @SerialName("products") val sets: List<Product>
)

/**
 * LCE
 */

sealed class LCE<out T>(
    val data: T? = null,
    val error: Throwable? = null
) {

    object Loading : LCE<Nothing>()
    class Complete<T>(data: T) : LCE<T>(data = data)
    class Error(throwable: Throwable) : LCE<Nothing>(error = throwable)
}