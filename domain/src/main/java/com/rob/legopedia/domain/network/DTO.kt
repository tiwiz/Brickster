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