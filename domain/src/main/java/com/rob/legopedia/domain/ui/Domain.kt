package com.rob.legopedia.domain.ui

import android.graphics.Bitmap

typealias LegoSetLCE = LCE<List<LegoSet>>

data class LegoSet(
    val id: String,
    val name: String,
    val image: Bitmap?,
    val theme: String,
    val year: Int
)