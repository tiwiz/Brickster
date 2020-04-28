@file:Suppress("ArrayInDataClass")

package com.rob.legopedia.domain.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val results: Array<LegoSetDTO>
)

/**
 * LEGO Service
 * */

@Serializable
data class InstructionsDTO(
    @SerialName("description") val description: String,
    @SerialName("pdfLocation") val pdfUrl: String,
    @SerialName("downloadSize") val downloadSize: String,
    @SerialName("frontpageInfo") val instructionsCover: String,
    @SerialName("oNum") val bookNumber: Int,
    @SerialName("isAlternative") val isAlternative: Boolean
)

@Serializable
data class ProductDTO(
    @SerialName("productId") val setId: String,
    @SerialName("productName") val setName: String,
    @SerialName("productImage") val setImage: String,
    @SerialName("themeName") val themeName: String,
    @SerialName("launchYear") val launchYear: Int,
    @SerialName("buildingInstructions") val instructions: Array<InstructionsDTO>
)

@Serializable()
data class LegoResponseDTO(
    @SerialName("count") val count: Int,
    @SerialName("totalCount") val totalCount: Int,
    @SerialName("products") val sets: Array<ProductDTO>
)