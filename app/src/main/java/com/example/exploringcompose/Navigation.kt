package com.example.exploringcompose


import android.Manifest.permission.*
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import com.google.accompanist.permissions.MultiplePermissionsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random


@Composable
fun HomeScreen(value: PaddingValues) {
    val data = listOf(
        img(R.drawable.image_3, "Elon Musk", "I'm founder of Tesla corp"),
        img(R.drawable.image_4, "Interstellar", "She found the way to survive!"),
        img(R.drawable.image_8, "Tech", "This new iphone destroys android market!"),
        img(R.drawable.image_9, "Tree of Life", "This magical tree will provide 1000+ benefits..."),
        img(R.drawable.image_3, "Elon Musk", "I'm founder of Tesla corp"),
        img(R.drawable.image_4, "Interstellar", "She found the way to survive!"),
        img(R.drawable.image_8, "Tech", "This new iphone destroys android market!"),
        img(R.drawable.image_9, "Tree of Life", "This magical tree will provide 1000+ benefits..."),
        img(R.drawable.image_3, "Elon Musk", "I'm founder of Tesla corp"),
        img(R.drawable.image_4, "Interstellar", "She found the way to survive!"),
        img(R.drawable.image_8, "Tech", "This new iphone destroys android market!"),
        img(R.drawable.image_9, "Tree of Life", "This magical tree will provide 1000+ benefits..."),
        img(R.drawable.image_3, "Elon Musk", "I'm founder of Tesla corp"),
        img(R.drawable.image_4, "Interstellar", "She found the way to survive!"),
        img(R.drawable.image_8, "Tech", "This new iphone destroys android market!"),
        img(R.drawable.image_9, "Tree of Life", "This magical tree will provide 1000+ benefits..."),
        img(R.drawable.image_3, "Elon Musk", "I'm founder of Tesla corp"),
        img(R.drawable.image_4, "Interstellar", "She found the way to survive!"),
        img(R.drawable.image_8, "Tech", "This new iphone destroys android market!"),
        img(R.drawable.image_9, "Tree of Life", "This magical tree will provide 1000+ benefits..."),
        img(R.drawable.image_3, "Elon Musk", "I'm founder of Tesla corp"),
        img(R.drawable.image_4, "Interstellar", "She found the way to survive!"),
        img(R.drawable.image_8, "Tech", "This new iphone destroys android market!"),
        img(R.drawable.image_9, "Tree of Life", "This magical tree will provide 1000+ benefits...")
    )

    generateImgCards(data = data, value)

}


@Composable
fun CounterScreen(paddingValue: PaddingValues, NavigateToAbout: (String, String) -> Unit) {
    val content = "Passed from CounterScreen"
    val content2 = "Testing"
    var counter by remember {
        mutableStateOf(0)
    }
    var snapshot = snapshotFlow { counter }

    var counterPowTwo = produceState(initialValue = counter) {
        snapshotFlow { counter }
            .map { it * it }
            .collect { value = it }
    }
    val derivedState by remember {
        derivedStateOf {
            "Current value : ${counterPowTwo.value}"
        }
    }

    Box(
        modifier = Modifier
            .padding(paddingValue)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "You're in counter screen")
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { NavigateToAbout(content, content2) }) {
                Text("Go to Animation Screen")
            }
            Spacer(modifier = Modifier.height(10.dp))
            val infiniteTransition = rememberInfiniteTransition()
            val animatedColor by infiniteTransition.animateColor(
                initialValue = Color.Red,
                targetValue = Color.Black,
                animationSpec = InfiniteRepeatableSpec(
                    tween(
                        durationMillis = 500,
                        delayMillis = 0,
                        easing = LinearEasing
                    ),
                    RepeatMode.Reverse
                )
            )
            var old_value by remember {
                mutableStateOf(counter)
            }

            SideEffect {
                old_value = counter
            }

            Row() {
                var new = counter.toString()
                var old = old_value.toString()
                for (i in new.indices) {
                    var oldChar = old.getOrNull(i)
                    var newChar = new[i]
                    var data = if (newChar == oldChar) {
                        oldChar
                    } else {
                        newChar
                    }
                    AnimatedContent(targetState = data,
                        transitionSpec = {
                            slideInVertically { it } togetherWith slideOutVertically { -it }
                        }
                    ) {
                        Text(text = it.toString(), softWrap = true, fontSize = 50.sp)
                    }
                }
            }


//            Text(text = counter, fontSize = 50.sp)


            Button(
                onClick = {
                    counter += 1
                },
                modifier = Modifier
                    .size(150.dp)
                    .aspectRatio(4f),
                colors = ButtonDefaults.buttonColors(containerColor = animatedColor)

            ) {
                Text(text = "Increment")
            }
            LaunchedEffect(key1 = counter) {
                delay(250)
                counter++
            }
//            LaunchedEffect(key1 = counter){
//                delay(1000L)
//                Log.d("counter-status", "Counter incremented")
//            }

        }
    }
}


@Composable
fun AnimationScreen(value: PaddingValues, content: String, content2: String) {

    var size by remember {
        mutableStateOf(16)
    }

    val textsize by animateIntAsState(
        targetValue = size,
        animationSpec = tween(
            durationMillis = 250,
            delayMillis = 0,
            easing = LinearEasing
        )
    )

    var robot by remember {
        mutableStateOf(true)
    }
    var checkstate by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(value)
    ) {
        Column(modifier = Modifier.weight(.1f)) {
            AnimatedVisibility(
                visible = robot,
            ) {
                Text(
                    "Robots are not allowed to use this app!",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red),
                    textAlign = TextAlign.Center,
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(value)
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            var transition = updateTransition(targetState = robot)
            val color1 by transition.animateColor(
                transitionSpec = { tween(2000) },
                label = "color1",
                targetValueByState = { robot ->
                    if (robot) Color.Red else Color.Green
                }
            )
            val color2 by transition.animateColor(
                transitionSpec = { tween(2000) },
                label = "color2",
                targetValueByState = { robot ->
                    if (robot) Color.Black else Color.Yellow
                }
            )
            val color3 by transition.animateColor(
                transitionSpec = { tween(2000) },
                label = "color3",
                targetValueByState = { robot ->
                    if (robot) Color.Blue else Color.Magenta
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(color1)
                        .padding(5.dp)
                        .size(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color2)
                        .padding(5.dp)
                        .size(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color3)
                        .padding(5.dp)
                        .size(50.dp)
                )
            }
            Button(onClick = { size += 10 }) {
                Text(text = "Increase size")
            }
            Text(text = content + " " + content2, fontSize = textsize.sp)

            Row(horizontalArrangement = Arrangement.Center) {
                Checkbox(checked = checkstate, onCheckedChange = {
                    robot = checkstate
                    checkstate = !checkstate
                }, modifier = Modifier.padding(0.dp))
                Text("Are you a human", modifier = Modifier.padding(top = 14.dp, start = 0.dp))
            }
            AnimatedContent(
                targetState = checkstate,
                transitionSpec = { slideInHorizontally { if (checkstate) it else -it } togetherWith slideOutHorizontally { if (checkstate) -it else it } },
            ) { state ->
                if (state) {
                    Text(
                        text = "Hey! Human",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                } else {
                    Text(
                        text = "Hey! Robot",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }

        }

    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScreen() {

    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val scope = rememberCoroutineScope()
    var state by remember {
        mutableStateOf("Open")
    }
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = { BottomSheetContent() },
        sheetBackgroundColor = Color.Green,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(5.dp),
        sheetPeekHeight = 0.dp,
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = {
                scope.launch {
                    if (scaffoldState.bottomSheetState.isCollapsed) {
                        state = "Close"
                        scaffoldState.bottomSheetState.expand()
                    } else {
                        state = "Open"
                        scaffoldState.bottomSheetState.collapse()
                    }
                }

            }) {
                Text("$state sheet")
            }
        }
    }

}

@Composable
fun BottomSheetContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp), contentAlignment = Alignment.Center
    ) {
        Text(text = "Bottom Sheet", fontWeight = FontWeight.Bold, fontSize = 30.sp)
    }
}

@Composable
fun SplashScreenAnimated(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1000)
        navController.popBackStack()
        navController.navigate(Screens.HomeScreen.route)
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.jetpack_compose),
            contentDescription = "Splash screen logo",
            modifier = Modifier.scale(scale.value),
        )
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(permissionState: MultiplePermissionsState, value: PaddingValues) {

    val modifier = Modifier.padding(5.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(value)
    ) {
        permissionState.permissions.forEach { permission ->
            when (permission.permission) {
                CAMERA -> {
                    when {
                        permission.status.isGranted -> {
                            Text(
                                text = "Camera permission is granted",
                                modifier = modifier
                            )
                        }

                        permission.status.shouldShowRationale -> {
                            Text(
                                text = "Camera permission is needed to access the camera",
                                modifier = modifier
                            )
                        }

                        !permission.status.isGranted && !permission.status.shouldShowRationale -> {
                            Text(
                                text = "Camera permission is permanently denied. To grant permission go to settings and do manually",
                                modifier = modifier
                            )
                        }
                    }
                }

                RECORD_AUDIO -> {
                    when {
                        permission.status.isGranted -> {
                            Text(
                                text = "Mic permission is granted",
                                modifier = modifier
                            )
                        }

                        permission.status.shouldShowRationale -> {
                            Text(
                                text = "Mic permission is needed to speak",
                                modifier = modifier
                            )
                        }

                        !permission.status.isGranted && !permission.status.shouldShowRationale -> {
                            Text(
                                text = "Mic permission is permanently denied. To grant permission go to settings and do manually",
                                modifier = modifier
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DropDownScreen(value: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(value)
    ) {
        DropDownBox(
            text = "Hello this is a detailed message. You clicked the ArrowDropDown icon",
            textcolor = Color.Black,
            boxcolor = Color.Green,
            open = false
        )
    }
}

@Composable
fun LongPressScreen(value: PaddingValues) {
    val data = listOf(
        "Jefi",
        "Ryan",
        "Jenifer",
        "Pearlin",
        "Christy",
        "Kanaga",
        "Latha",
        "Jefi",
        "Ryan",
        "Jenifer",
        "Pearlin",
        "Christy",
        "Kanaga",
        "Latha",
    )
    Box(
        modifier = Modifier
            .padding(value)
            .fillMaxSize()
    ) {
        LazyColumn() {
            items(data) {
                PersonItem(it)
            }
        }
    }
}

@Composable
fun MultiSelectScreen(value: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(value)
    ) {
        val data = listOf(
            PersonContent("Jefi"),
            PersonContent("Ryan"),
            PersonContent("Jenifer"),
            PersonContent("Pearlin"),
            PersonContent("Christy"),
            PersonContent("Kanaga"),
            PersonContent("Latha"),
            PersonContent("Jefi"),
            PersonContent("Ryan"),
            PersonContent("Jenifer"),
            PersonContent("Pearlin"),
            PersonContent("Christy"),
            PersonContent("Kanaga"),
            PersonContent("Latha"),
        )
        MultiSelectItem(data = data)
    }
}

@Composable
fun RandomSizeWidgetScreen(value : PaddingValues) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(100.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(value),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        items(1000) {
            val height = Random.nextInt(50, 300)
            val width = Random.nextInt(50, 300)
            val color =
                Color(Random.nextInt(0, 255), Random.nextInt(0, 255), Random.nextInt(0, 255))
            Box(
                modifier = Modifier
                    .size(width = width.dp, height = height.dp)
                    .background(color)
            )
        }
    }
}

@Preview
@Composable
fun preview() {
    RandomSizeWidgetScreen(value = PaddingValues(0.dp))
}