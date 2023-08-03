package com.example.exploringcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val multiplePermissionsState = handle_permission()
            val scope = rememberCoroutineScope()
            val NavController = rememberNavController()
            val backStackEntry = NavController.currentBackStackEntryAsState()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scrollState = rememberScrollState()
            val menu_items = listOf(
                MItem(Icons.Default.Home, "Home"),
                MItem(Icons.Default.Settings, "Counter"),
                MItem(Icons.Default.Info, "Animation"),
                MItem(Icons.Default.Menu, "Bottom Sheet"),
                MItem(Icons.Default.Info, "Permission"),
                MItem(Icons.Default.MoreVert, "Drop Down"),
                MItem(Icons.Default.List, "Long Press"),
                MItem(Icons.Default.Check, "Multi Select"),
                MItem(Icons.Default.GridView, "Staggered Grid"),
            )
            val btm_items = listOf(
                NavItem("Home",Icons.Default.Home,0),
                NavItem("Counter",Icons.Default.Settings,32),
                NavItem("Animation",Icons.Default.Info,124),
            )
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet{
                        Column(modifier = Modifier.fillMaxHeight().width(300.dp)) {
                            DrawerHeader()
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .verticalScroll(state = scrollState)
                            ) {
                                menu_items.forEach(){
                                    val route = when(it.title){
                                        "Home" -> Screens.HomeScreen.route
                                        "Counter" -> Screens.CounterScreen.route
                                        "Animation" -> Screens.AnimationScreen.route + "?content={content}?data={data}"
                                        "Bottom Sheet" -> Screens.BottomSheetScreen.route
                                        "Permission" -> Screens.PermissionScreen.route
                                        "Drop Down" -> Screens.DropDownScreen.route
                                        "Long Press" -> Screens.LongPressScreen.route
                                        "Multi Select" -> Screens.MultiSelectScreen.route
                                        "Staggered Grid" -> Screens.RandomSizeWidgetScreen.route
                                        else -> Screens.HomeScreen.route
                                    }
                                    NavigationDrawerItem(
                                        shape = RectangleShape,
                                        label = {
                                            Row() {
                                                Icon(imageVector = it.icon, contentDescription = null)
                                                Spacer(modifier = Modifier.width(5.dp))
                                                Text(text = it.title)
                                            }
                                        },
                                        selected = false,
                                        onClick = {
                                            NavController.navigate(
                                                route = route,
                                            )
                                            scope.launch {
                                                drawerState.close()
                                            }
                                        })
                                }
                                Column(modifier = Modifier.padding(start = 10.dp, top = 110.dp)){
                                    Text(text = "V1.0")
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(text = "Developer : Jefi Ryan")
                                }
                            }
                        }
                    }

                },
            ) {
                Scaffold(
                    topBar = {
                        LaunchedEffect(key1 = backStackEntry){
                            Log.d("Destination",backStackEntry.value?.destination?.route ?: "null")
                        }
                        if(isValidScreen(backStackEntry.value?.destination?.route)) {
                            AppBar(
                                onNavIconClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            )
                        }
                    },
                    bottomBar = {
                        if(isValidScreen(backStackEntry.value?.destination?.route)) {
                            BottomBar(
                                items = btm_items,
                                navigate = { NavController.navigate(it + "Screen") },
                                backStackEntry = backStackEntry
                            )
                        }
                    },
                    content = {value ->
                        NavHost(navController = NavController, startDestination = Screens.SplashScreen.route){
                            composable(
                                route = Screens.SplashScreen.route
                            ){
                                SplashScreenAnimated(navController = NavController)
                            }
                            composable(
                                route = Screens.HomeScreen.route,
                            ){
                                HomeScreen(value = value)
                            }
                            composable(
                                route = Screens.CounterScreen.route,                            ){ entry ->
                                CounterScreen(paddingValue = value,
                                    NavigateToAbout = {a,b ->
                                        NavController.navigate(Screens.AnimationScreen.route + "?content=$a?data=$b")
                                    }
                                )
                            }
                            composable(
                                route = "${Screens.AnimationScreen.route}?content={content}?data={data}",
                                arguments = listOf(
                                    navArgument(name = "content",
                                    builder = {
                                        type = NavType.StringType
                                        defaultValue = "Hello"
                                    }
                                ),
                                    navArgument(name = "data",
                                        builder = {
                                            type = NavType.StringType
                                            defaultValue = "Jefi"
                                        }
                                    )
                                )
                            ){
                                val data1 = it.arguments?.getString("content") as String
                                val data2 = it.arguments?.getString("data") as String
                                Log.d("Navigation","Navigation argument : $data1, $data2")
                                AnimationScreen(value = value, content = data1, content2 = data2)
                            }
                            composable(
                                route = Screens.BottomSheetScreen.route
                            ){
                                BottomSheetScreen()
                            }
                            composable(
                                route = Screens.PermissionScreen.route
                            ){
                                PermissionScreen(multiplePermissionsState, value)
                            }
                            composable(
                                route = Screens.DropDownScreen.route
                            ){
                                DropDownScreen(value)
                            }
                            composable(
                                route = Screens.LongPressScreen.route
                            ){
                                LongPressScreen(value)
                            }
                            composable(
                                route = Screens.MultiSelectScreen.route
                            ){
                                MultiSelectScreen(value)
                            }
                            composable(
                                route = Screens.RandomSizeWidgetScreen.route
                            ){
                                RandomSizeWidgetScreen(value)
                            }
                        }
                    }
                )
            }

        }
    }

    private fun isValidScreen(screen : String?) : Boolean{
        val currentscreen : String
        val allowedScreens = listOf(
            Screens.HomeScreen.route,
            Screens.AnimationScreen.route,
            Screens.CounterScreen.route,
            Screens.BottomSheetScreen.route,
            Screens.PermissionScreen.route,
            Screens.DropDownScreen.route,
            Screens.LongPressScreen.route,
            Screens.MultiSelectScreen.route,
            Screens.RandomSizeWidgetScreen.route,
        )
        if(screen == null){
            return false
        } else {
            currentscreen = screen
        }
        allowedScreens.forEach { allowed_screen ->
            if(currentscreen.contains(allowed_screen)){
                return true
            }
        }
        return false
    }
}

