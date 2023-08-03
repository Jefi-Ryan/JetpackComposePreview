package com.example.exploringcompose

import android.graphics.drawable.shapes.Shape
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MultiSelectItem(data : List<PersonContent>) {

    val stateData by remember{
        mutableStateOf(data)
    }

    var longpress by rememberSaveable {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxSize()){
        AnimatedVisibility(visible = longpress) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black), horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    Log.d(
                        "Delete",
                        stateData.filter { it.selected }.toString()
                    )
                    for (i in data){
                        if (i.selected){
                            i.selected = false
                        }
                    }
                    longpress = false
                },
                    content = {Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.White)})
            }
        }

        LazyColumn{
            items(stateData) { person ->
                var selectedState by rememberSaveable{
                    mutableStateOf(person.selected)
                }

                val interaction = remember {
                    MutableInteractionSource()
                }

                Box(modifier = Modifier
                    .fillMaxSize()
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .indication(interactionSource = interaction, indication = rememberRipple())
                        .pointerInput(true) {
                            detectTapGestures(
                                onLongPress = {
                                    longpress = true
                                },
                                onPress = {
                                    val press = PressInteraction.Press(it)
                                    val release = PressInteraction.Release(press = press)
                                    interaction.emit(press)
                                    tryAwaitRelease()
                                    interaction.emit(release)
                                    if (longpress) {
                                        selectedState = !selectedState
                                        person.selected = !person.selected
                                    }
                                }
                            )
                        }
                        .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(text = person.name)
                        if (longpress && selectedState) {
                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
                        }
                        if (longpress && !selectedState){
                            Icon(imageVector = Icons.Default.RadioButtonUnchecked, contentDescription = null)
                        }
                    }
                    Divider()

                }
            }
        }

    }

}