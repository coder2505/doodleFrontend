package com.example.doodlefrontend.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.R
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import com.example.doodlefrontend.ui.theme.notcursiveFont

@Composable
@Preview

fun JoinRoom(navController: NavController = rememberNavController()) {

    DoodleFrontendTheme {

        Scaffold { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.weight(1f))

                doodleTextfield()

                Spacer(modifier = Modifier.weight(1f))
                Box {


                        doodleButton {


                        }


                }


            }

        }

    }

}


@Composable
fun doodleTextfield(){

    BasicTextField(
        state = rememberTextFieldState("enter here"),
        modifier = Modifier
            .width(280.dp)
            .height(75.dp)
            .paint(
                painter = painterResource(id = R.drawable.doodlerectangle2),
                contentScale = ContentScale.FillBounds
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        textStyle = TextStyle(fontFamily = notcursiveFont, fontSize = 24.sp)
    )

}