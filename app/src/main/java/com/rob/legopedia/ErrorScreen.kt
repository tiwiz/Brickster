package com.rob.legopedia

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.graphics.painter.ImagePainter
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

@Composable
fun ErrorScreen(onImeAction: (String) -> Unit = {}) {
    MaterialTheme {
        Column {
            SearchView(onImeAction)

            Row(arrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Column(arrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                    Box(modifier = Modifier.preferredWidth(width = 300.dp)) {
                        Image(painter = ImagePainter(image = imageResource(id = R.drawable.ic_error)))
                    }
                    Text("Something went wrong, try and search again.")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ErrorScreen() {
    ErrorScreen()
}