package com.example.doodlefrontend.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.R
import com.example.doodlefrontend.Routes
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import com.example.doodlefrontend.ui.theme.notcursiveFont
import com.example.doodlefrontend.viewmodels.JoinRoomUIEvents
import com.example.doodlefrontend.viewmodels.JoinRoomViewModel
import com.example.doodlefrontend.views.createroom.doodleButton

@Composable
@Preview

fun JoinRoom(
    navController: NavController = rememberNavController(),
    joinRoomViewModel: JoinRoomViewModel = hiltViewModel()
) {

    var textFieldState = rememberTextFieldState("")
    val SnackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(Unit) {

        joinRoomViewModel.sharedFlow.collect { events ->


            when (events) {

                is JoinRoomUIEvents.ShowSnackBar -> SnackbarHostState.showSnackbar(
                    events.message
                )

                is JoinRoomUIEvents.Success -> {

                    navController.navigate(Routes.HomeScreen)


                }


            }


        }


    }



    DoodleFrontendTheme {

        Scaffold(
            snackbarHost = { SnackbarHost(SnackbarHostState) }
        ) { innerPadding ->

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


                    doodleButton {

                        joinRoomViewModel.joinRoom(textFieldState.text.toString())

                    }


                }


            }

        }

    }

}


@Composable
fun DoodleTextfield(textFieldState: TextFieldState) {

    BasicTextField(
        state = textFieldState,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
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