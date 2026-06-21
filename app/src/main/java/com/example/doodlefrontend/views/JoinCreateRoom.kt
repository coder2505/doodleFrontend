package com.example.doodlefrontend.views

import android.graphics.Paint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.R
import com.example.doodlefrontend.Routes
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import com.example.doodlefrontend.ui.theme.cursiveFont

@Composable
@Preview
fun JoinCreateRoom(navController: NavController = rememberNavController()) {


    DoodleFrontendTheme {

        Scaffold(modifier = Modifier.fillMaxSize()) { padding ->

            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterVertically)
            ) {

                JoinRoomButton {
                    navController.navigate(Routes.JoinRoomScreen)
                }

                CreateRoomButton {
                    navController.navigate(Routes.CreateRoomScreen)
                }


            }

        }


    }

}


@Preview
@Composable
fun JoinRoomButton(onclick : () -> Unit = {}){

        Button(
            onClick = onclick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier.paint(
                painter = painterResource(R.drawable.doodlerectagle1)
            )
        ) {
            Text("Join a room", color = Color.Black, fontFamily = cursiveFont, fontSize = 32.sp)
        }


}

@Preview
@Composable
fun CreateRoomButton(onclick : () -> Unit = {}) {

    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.paint(
            painter = painterResource(R.drawable.doodlerectangle2)
        )
    ) {
        Text("Create a room", color = Color.Black, fontFamily = cursiveFont, fontSize = 32.sp)
    }

}




