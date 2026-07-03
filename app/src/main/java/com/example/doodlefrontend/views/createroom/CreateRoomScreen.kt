package com.example.doodlefrontend.views.createroom

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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.R
import com.example.doodlefrontend.Routes
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import com.example.doodlefrontend.viewmodels.CreateRoomViewModel
import com.example.doodlefrontend.viewmodels.UIEventsCreateRoom
import com.example.doodlefrontend.views.DoodleTextfield

@Preview
@Composable
fun CreateRoom(
    navController: NavController = rememberNavController(),
    createRoomViewModel: CreateRoomViewModel = hiltViewModel()
) {

    var roomNameState = rememberTextFieldState("your room name ")
    var snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(Unit) {

        createRoomViewModel.sharedFlow.collect { event ->
            when(event){

                is UIEventsCreateRoom.ShowSnackBar -> snackbarHostState.showSnackbar(event.message)
                is UIEventsCreateRoom.roomCreated -> navController.navigate(Routes.CreateRoomScreen2)

            }
        }

    }



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
                DoodleTextfield(roomNameState)

                Spacer(modifier = Modifier.weight(1f))
                Box {

                    Column() {

                        doodleButton {

                            createRoomViewModel.createRoom(roomNameState.text.toString())

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