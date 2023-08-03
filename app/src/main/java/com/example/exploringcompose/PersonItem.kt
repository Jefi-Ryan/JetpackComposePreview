package com.example.exploringcompose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun PersonItem(name: String) {
    var state by rememberSaveable {
        mutableStateOf(false)
    }
    var offset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    val interaction = remember {
        MutableInteractionSource()
    }
    val ripple = rememberRipple()
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = Modifier
            .onSizeChanged {
                itemHeight = with(density.density) { it.height.dp }
            }
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .indication(interaction, ripple)
            .pointerInput(true) {
                detectTapGestures(
                    onLongPress = {
                        state = true
                        Log.d("Offset", "dp : ${it.x.dp}, toDp : ${it.x.toDp()}")
                        offset = DpOffset(it.x.toDp(), it.y.toDp())
                    },
                    onPress = {
                        val press = PressInteraction.Press(it)
                        val release = PressInteraction.Release(press = press)
                        interaction.emit(press)
                        tryAwaitRelease()
                        interaction.emit(release)
                    }
                )
            }
            .padding(16.dp),
        ){
            Text(text = name, fontWeight = FontWeight.Bold)
        }
        val data = listOf(
            "Call",
            "Call history",
            "Edit",
            "Delete",
        )
        val context = LocalContext.current

        DropdownMenu(
            expanded = state,
            onDismissRequest = { state = false },
            offset = offset.copy(y = offset.y - itemHeight)
        ) {
            data.forEach {
                DropdownMenuItem(text = { Text(it) }, onClick = {
                    Toast.makeText(context,"$it is clicked!",Toast.LENGTH_SHORT).show()
                    state = false
                })
            }


        }
    }

}