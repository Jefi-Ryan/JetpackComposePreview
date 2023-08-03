package com.example.exploringcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberWindowInfo() : WindowInfo {
    val config = LocalConfiguration.current
    val heightInfo = when {
        (config.screenHeightDp < 600) -> WindowInfo.WindowType.Compact
        (config.screenHeightDp < 840) -> WindowInfo.WindowType.Medium
        else -> WindowInfo.WindowType.Extended
    }
    val widthInfo = when {
        (config.screenWidthDp < 480) -> WindowInfo.WindowType.Compact
        (config.screenWidthDp < 900) -> WindowInfo.WindowType.Medium
        else -> WindowInfo.WindowType.Extended
    }
    val data = WindowInfo(
        screenWidthInfo = widthInfo,
        screenHeightInfo = heightInfo,
        screenHeight = config.screenHeightDp.dp,
        screenWidth = config.screenWidthDp.dp
    )
    return data
}

data class WindowInfo(
    val screenWidthInfo : WindowType,
    val screenHeightInfo : WindowType,
    val screenWidth : Dp,
    val screenHeight : Dp,
) {
    sealed class WindowType{
        object Compact : WindowType()
        object Medium : WindowType()
        object Extended : WindowType()
    }
}
