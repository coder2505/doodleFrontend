package com.example.doodlefrontend.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.R
import com.example.doodlefrontend.configurations.RetrofitInstance
import com.example.doodlefrontend.repository.CreateRoomRepo
import com.example.doodlefrontend.security.TokenManager
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import kotlinx.coroutines.launch

@Preview
@Composable
fun CreateRoom(navController: NavController = rememberNavController()) {

    var textFieldState = rememberTextFieldState("your room name ")

    DoodleFrontendTheme {

        Scaffold() { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.weight(1f))
                DoodleTextfield(textFieldState)

                Spacer(modifier = Modifier.weight(1f))
                Box {

                    Column() {
//                        doodleButton(photoId = painterResource(R.drawable.sharedoodle)) {
//
//                        }
                        doodleButton {


                        }

                    }

                }


            }

        }

    }

}

@Composable
@Preview
fun doodleButton(
    photoId: Painter = painterResource(R.drawable.arrowdoodle),
    onclick: () -> Unit = {}
) {

    val width: Int = LocalConfiguration.current.screenWidthDp
    val height: Int = LocalConfiguration.current.screenHeightDp

    val painterResource = photoId

    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
        modifier = Modifier
            .width((width * 0.6).dp)
            .height((height * 0.18).dp)
            .paint(
                painter = painterResource(R.drawable.doodlerectagle1),
                contentScale = ContentScale.FillBounds
            )
    ) {

        Image(
            painter = painterResource,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(0.7f)
        )

    }

}