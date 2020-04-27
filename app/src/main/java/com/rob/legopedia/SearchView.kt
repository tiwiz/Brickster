package com.rob.legopedia

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.Border
import androidx.ui.foundation.DrawBorder
import androidx.ui.foundation.Text
import androidx.ui.foundation.TextField
import androidx.ui.graphics.Color
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.padding
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.unit.dp
import androidx.ui.unit.sp

@Composable
fun SearchView(onImeAction: (String) -> Unit) {
    val state = state { "" }
    Column {
        Text(
            text = "Search by LEGO™ set id",
            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp),
            style = TextStyle(
                color = Color.LightGray,
                fontSize = 12.sp
            )
        )
        Row(modifier = Modifier.padding(4.dp)) {
            TextField(
                value = state.value,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Search,
                textStyle = TextStyle(fontFamily = FontFamily.Monospace),
                onImeActionPerformed = { onImeAction(state.value) },
                onValueChange = { state.value = it },
                modifier = DrawBorder(border = Border(1.dp, color = Color.LightGray))
            )
        }
    }
}