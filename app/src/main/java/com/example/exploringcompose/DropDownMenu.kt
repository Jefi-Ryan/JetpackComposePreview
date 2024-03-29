package com.example.exploringcompose

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DropDownBox(
    text: String,
    textcolor: Color,
    boxcolor: Color,
    open: Boolean
) {
    var state by remember {
        mutableStateOf(open)
    }

    val alpha = animateFloatAsState(
        targetValue = if (state) 1f else 0.4f,
        animationSpec = tween(250)
    )

    val rotateX = animateFloatAsState(
        targetValue = if (state) 0f else 90f,
        animationSpec = tween(250)
    )
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable { state = !state },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(text = "More details")
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.scale(1f, if (state) -1f else 1f)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    transformOrigin = TransformOrigin(0.5f, 0f)
                    rotationX = rotateX.value
                }
                .alpha(alpha.value)
                .background(boxcolor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                textAlign = TextAlign.Center,
                color = textcolor,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Composable
fun DropDownForAppBar(open: Boolean) {
    val data = listOf(
        "Item 1",
        "Item 2",
        "Item 3",
        "Item 4",
        "Item 5",
    )
    var state by remember {
        mutableStateOf(open)
    }
    val context = LocalContext.current
    DropdownMenu(expanded = state,
        onDismissRequest = {
            state = false
        }) {
        data.forEach {
            DropdownMenuItem(text = { Text(text = it) }, onClick = {
                Toast.makeText(context, "$it is pressed!", Toast.LENGTH_SHORT).show()
            })
        }
    }

}

@Preview(showBackground = true)
@Composable
fun prev() {
    DropDownBox(text = "Hello Jefi", textcolor = Color.Black, boxcolor = Color.Green, open = false)
}