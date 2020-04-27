package com.rob.legopedia.domain.ui

import android.graphics.Bitmap
import com.rob.legopedia.domain.network.LegoSetDTO
import com.rob.legopedia.domain.network.ResponseDTO
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun LegoSetDTO.toLegoSet() =
    LegoSet(
        id = setNum,
        name = name,
        image = downloadImageFrom(imageUrl),
        detailUrl = setUrl
    )

private suspend fun downloadImageFrom(imageUrl: String?): Bitmap? =
    withContext(Dispatchers.IO) {
        Picasso.get().load(imageUrl).get()
    }

suspend fun ResponseDTO.toLegoSets() =
    if (count > 0) {
        results.map { it.toLegoSet() }
    } else {
        emptyList()
    }