package com.example.exploringcompose

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ImageCard(img : Int, title : AnnotatedString, description : String) {
    val painter = painterResource(id = img)
    Card(modifier = Modifier
        .height(240.dp)
        .width(410.dp)
        .padding(10.dp),
        shape = RoundedCornerShape(5.dp),
    ){
        Box {
            Image(painter = painter, contentDescription = description,
                Modifier.fillMaxSize())
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 300f
                    ),
                )
            )
            Box(contentAlignment = Alignment.BottomStart, modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)) {
                Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp, modifier = Modifier.padding(bottom = 30.dp))
                Text(text = description, color = Color.White, fontSize = 15.sp, modifier = Modifier.padding(bottom = 9.dp))
            }
        }
    }
}

@Composable
fun generateImgCards(data : List<img>, value : PaddingValues) {
    val windowInfo = rememberWindowInfo()
    if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
        LazyColumn(modifier = Modifier.padding(value)){
            items(data){
                ImageCard(it.img,
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red, fontSize = 45.sp
                            )
                        ){
                            append(it.title[0])
                        }
                        append(it.title.substring(1))
                    },
                    it.description)
            }
        }
    } else if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium) {
        val gridState = rememberLazyGridState()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            state = gridState,
            modifier = Modifier.padding(value)){
            items(data){
                ImageCard(it.img,
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red, fontSize = 45.sp
                            )
                        ){
                            append(it.title[0])
                        }
                        append(it.title.substring(1))
                    },
                    it.description)
            }
        }
    } else {
        val gridState = rememberLazyGridState()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            state = gridState,
            modifier = Modifier.padding(value)){
            items(data){
                ImageCard(it.img,
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red, fontSize = 45.sp
                            )
                        ){
                            append(it.title[0])
                        }
                        append(it.title.substring(1))
                    },
                    it.description)
            }
        }
    }
}


@Composable
fun card(text : String, description : String) {
    val size by animateDpAsState(
        targetValue = 150.dp,
        tween(
            durationMillis = 2000,
            delayMillis = 1000
        )
    )

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(size)
        .padding(10.dp)){
        Text(text = text, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 5.dp, top = 5.dp, bottom = 5.dp))
        Text(text = description, modifier = Modifier.padding(5.dp))
    }
}

@Composable
fun generateCards(count : Int) {

    LazyColumn(){
        items(count){
            card(text = "Hi", description = "This is Jefi Ryan")
        }
    }
}





@Composable
fun Loading(){



    val infiniteTransition = rememberInfiniteTransition()
    val value by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        )
    )

    Column(verticalArrangement = Arrangement.Center,modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .padding(150.dp)){
        Box(modifier = Modifier.fillMaxWidth()){
            Canvas(modifier = Modifier.size(100.dp)){
                drawArc(color = Color.Yellow,
                    startAngle = -90f,
                    sweepAngle = value,
                    useCenter = false,
                    style = Stroke(width = 20f, cap = StrokeCap.Round)
                )
            }
        }
//
//        Spacer(modifier = Modifier.height(10.dp))
//        Button(onClick = {value = if(value <= 355) value + 5 else 0f}) {
//            Text(text = "Load!", textAlign = TextAlign.Center, color = Color.White)
//        }
    }

}
