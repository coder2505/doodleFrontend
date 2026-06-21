package com.example.doodlefrontend.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.R
import com.example.doodlefrontend.Routes
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import com.example.doodlefrontend.ui.theme.cursiveFont
import com.example.doodlefrontend.ui.theme.notcursiveFont


@Composable
@Preview
fun NameScreen(navController: NavController = rememberNavController()) {

    DoodleFrontendTheme {

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    15.dp,
                    alignment = Alignment.CenterVertically

                )
            ) {
                Spacer(Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text("Name", fontFamily = cursiveFont, fontSize = 32.sp, color = Color.Black)
                    Image(
                        painter = painterResource(R.drawable.cursordoodle),
                        contentDescription = "",
                        modifier = Modifier
                            .rotate(270f)
                            .size(50.dp)
                    )

                }
                doodleTextfield()
                Spacer(Modifier.weight(1f))

                Box(modifier = Modifier.padding(32.dp)) {
                    button {
                        navController.navigate(Routes.JoinCreateScreen)
                    }
                }



            }

        }

    }

}



