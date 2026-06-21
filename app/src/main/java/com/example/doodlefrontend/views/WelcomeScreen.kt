package com.example.doodlefrontend.views

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.R
import com.example.doodlefrontend.Routes
import com.example.doodlefrontend.model.DoodleAsteroid
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import com.example.doodlefrontend.ui.theme.cursiveFont
import com.example.doodlefrontend.ui.theme.notcursiveFont
import com.example.doodlefrontend.ui.theme.yellow
import kotlin.random.Random

@Composable
@Preview
fun WelcomeScreen(navController: NavController = rememberNavController()) {

    DoodleFrontendTheme {

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            FloatingBackground {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(
                    25.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                text1("Connect with your loved ones,")
                text2()
                text1("at a time.")

                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier.padding(bottom = 32.dp)) {
                    button({
                        navController.navigate(Routes.NameScreen)
                    })
                }


            }


            }

        }

    }

}


@Composable
@Preview
fun button(onclick : ()-> Unit = {}) {

    val width: Int = LocalConfiguration.current.screenWidthDp
    val height: Int = LocalConfiguration.current.screenHeightDp

    val painterResource = painterResource(R.drawable.arrowdoodle)

    OutlinedButton(
        onClick = onclick,
        modifier = Modifier
            .width((width * 0.5).dp)
            .height((height * 0.1).dp),
        colors = ButtonDefaults.buttonColors(yellow),
        border = BorderStroke(width = 6.dp, color = Color.Black)
    ) {

        Image(
            painter = painterResource,
            contentDescription = null
        )

    }

}

@Preview
@Composable
fun text1(text: String = "Connect with your loved ones,") {
    Text(
        text,
        fontFamily = notcursiveFont,
        fontSize = 26.sp,
        textAlign = TextAlign.Center,
        color = Color.Black
    )
}

@Preview
@Composable
fun text2() {
    Text(
        "One Doodle",
        fontFamily = cursiveFont,
        fontSize = 48.sp,
        textAlign = TextAlign.Center,
        color = Color.Black
    )
}

@Composable
fun FloatingBackground(content: @Composable () -> Unit = {}) {

    var images = listOf<Int>(
        R.drawable.appledoodle,
        R.drawable.liondoodle,
        R.drawable.treedoodle,
        R.drawable.beardoodle,
        R.drawable.birddoodle,
        R.drawable.sundoodle,
        R.drawable.eyedoodle
    )

    val doodles = remember {

        images.map { image ->
            DoodleAsteroid(
                resId = image,
                startX = Random.nextFloat() * 400,
                startY = Random.nextFloat() * 800,
                speedX = Random.nextFloat() * 2 - 1f,
                speedY = Random.nextFloat() * 2 - 1f
            )
        }

    }

    val infiniteTransition = rememberInfiniteTransition(label = "floating")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 50000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "time"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        doodles.forEach { doodle ->
            val currentX = (doodle.startX + doodle.speedX * time) % 400
            val currentY = (doodle.startY + doodle.speedY * time) % 800

            Image(
                painter = painterResource(id = doodle.resId),
                contentDescription = null,
                modifier = Modifier.offset(x = currentX.dp, y = currentY.dp)
            )
        }

        content()

    }
}
