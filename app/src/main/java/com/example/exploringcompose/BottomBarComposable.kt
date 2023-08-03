package com.example.exploringcompose

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(items: List<NavItem>, navigate: (String) -> Unit, backStackEntry: State<NavBackStackEntry?>) {
    val selected = backStackEntry.value?.destination?.route
    Log.d("StackTop",selected ?: "null")
    BottomNavigation(backgroundColor = Color.White, elevation = 30.dp, modifier = Modifier.height(60.dp)
    ) {
        items.forEach {item ->
            val selectedstate = selected?.contains(item.title) == true
            BottomNavigationItem(
                selected = selectedstate,
                onClick = { navigate(item.title) },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Black,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (selectedstate){
                            Log.d("SelectedBottomNavItem", item.title)
                            Icon(imageVector = item.icon, contentDescription = item.title, tint = Color.Red)
                            Text(text = item.title)
                            item.count = 0
                        } else {
                            if(item.count > 0){
                                BadgedBox(
                                    badge = { Text(text = item.count.toString(), modifier = Modifier.clip(CircleShape).background(Color.Red).padding(2.dp), color = Color.White)},
                                    content = {

                                    }
                                )
                            }

                            Icon(imageVector = item.icon, contentDescription = item.title, tint = Color.Black)
                        }
                    }
                }
            )
        }
    }
}

