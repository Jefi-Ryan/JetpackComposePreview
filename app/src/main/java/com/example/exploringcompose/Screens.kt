package com.example.exploringcompose

sealed class Screens(val route : String){

    object SplashScreen : Screens("SplashScreen")
    object HomeScreen : Screens("HomeScreen")
    object CounterScreen : Screens("CounterScreen")
    object AnimationScreen : Screens("AnimationScreen")
    object BottomSheetScreen : Screens("BottomSheetScreen")
    object PermissionScreen : Screens("PermissionScreen")
    object DropDownScreen : Screens("DropDownScreen")
    object LongPressScreen : Screens("LongPressScreen")
    object MultiSelectScreen : Screens("MultiSelectScreen")
    object RandomSizeWidgetScreen : Screens("RandomSizeWidgetScreen")

}
