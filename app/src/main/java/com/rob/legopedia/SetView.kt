package com.rob.legopedia

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Image
import androidx.ui.graphics.Color
import androidx.ui.graphics.asImageAsset
import androidx.ui.graphics.painter.ImagePainter
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.DpConstraints
import androidx.ui.layout.padding
import androidx.ui.material.Card
import androidx.ui.material.ListItem
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.ripple
import androidx.ui.res.colorResource
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.rob.legopedia.domain.ui.LegoSet

@Composable
fun SetView(
    items: List<LegoSet>,
    onImeAction: (String) -> Unit = {},
    onClick: (LegoSet) -> Unit = {}
) {
    MaterialTheme {
        Column {
            SearchView(onImeAction = onImeAction)
            AdapterList(data = items) {
                LegoSetCard(it, onClick = onClick)
            }
        }
    }
}

@Composable
private fun LegoSetCard(it: LegoSet, onClick: (LegoSet) -> Unit = {}) {
    val image = it.image?.asImageAsset() ?: imageResource(id = R.drawable.ic_empty)

    Clickable(onClick = { onClick(it) }, modifier = Modifier.ripple(color = colorResource(id = R.color.colorAccent))) {
        Card(modifier = Modifier.padding(top = 4.dp, start = 4.dp, end = 4.dp)) {
            Column {
                Container(expanded = true, alignment = Alignment.Center, constraints = DpConstraints(maxHeight = 200.dp)) {
                    Image(painter = ImagePainter(image = image))
                }
                ListItem(text = it.name, overlineText = it.id, secondaryText = it.detailUrl)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SetView() {

    val items = listOf(
        LegoSet("1", "Set 1", null, "www.google.it"),
        LegoSet("2", "Set 2", null, "www.google.it")
    )

    SetView(items)
}