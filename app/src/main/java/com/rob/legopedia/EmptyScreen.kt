package com.rob.legopedia

import androidx.compose.Composable
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.graphics.painter.ImagePainter
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

@Composable
fun EmptyScreen(
    onImeAction: (String) -> Unit = {},
    emptyMessage: String = "Search some LEGO™ sets"
) {
    MaterialTheme {
        Column {
            SearchView(onImeAction)

            Row(arrangement = Arrangement.Center, modifier = LayoutWidth.Fill) {
                Column(arrangement = Arrangement.Center, modifier = LayoutHeight.Fill) {
                    Container(width = 200.dp, height = 200.dp) {
                        Image(painter = ImagePainter(image = imageResource(id = R.drawable.ic_empty)))
                    }
                    Text(emptyMessage)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    EmptyScreen()
}