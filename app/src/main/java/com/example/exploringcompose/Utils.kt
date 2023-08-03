package com.example.exploringcompose

import androidx.compose.ui.graphics.vector.ImageVector

data class img(val img: Int, val title: String, val description: String)

data class MItem(val icon: ImageVector, val title: String)

data class NavItem(
    val title: String,
    val icon: ImageVector,
    var count: Int,
)

data class PersonContent(val name: String, var selected: Boolean = false)