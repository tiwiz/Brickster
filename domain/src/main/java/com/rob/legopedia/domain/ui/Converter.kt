package com.rob.legopedia.domain.ui

import android.graphics.Bitmap
import com.rob.legopedia.domain.network.LegoResponseDTO
import com.rob.legopedia.domain.network.ProductDTO
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun ProductDTO.toLegoSet() =
    LegoSet(
        id = setId,
        name = setName,
        image = downloadImageFrom(setImage),
        theme = themeName,
        year = launchYear
    )

private suspend fun downloadImageFrom(imageUrl: String?): Bitmap? =
    withContext(Dispatchers.IO) {
        Picasso.get().load(imageUrl).get()
    }

suspend fun LegoResponseDTO.toLegoSets() =
    if (count > 0) {
        sets.map { it.toLegoSet() }
    } else {
        emptyList()
    }