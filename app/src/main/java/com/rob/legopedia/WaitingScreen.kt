package com.rob.legopedia

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.graphics.painter.ImagePainter
import androidx.ui.layout.*
import androidx.ui.material.LinearProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

@Composable
fun WaitingScreen() {
    MaterialTheme {
        Row(arrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Column(arrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                Box(modifier = Modifier.preferredSize(width = 200.dp, height = 200.dp)) {
                    Image(painter = ImagePainter(image = imageResource(id = R.drawable.ic_loading)))
                }
                LinearProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_WaitingScreen() {
    WaitingScreen()
}