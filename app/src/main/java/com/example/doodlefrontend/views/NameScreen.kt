package com.example.doodlefrontend.views

import android.util.Log
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.R
import com.example.doodlefrontend.Routes
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import com.example.doodlefrontend.ui.theme.cursiveFont
import com.example.doodlefrontend.viewmodels.SubmitName
import com.example.doodlefrontend.viewmodels.UIevents


@Composable
@Preview
fun NameScreen(
    navController: NavController = rememberNavController(),
    viewModel: SubmitName = viewModel()
) {

    var textFieldState = rememberTextFieldState("")

    val SnackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {

        viewModel.events.collect { event ->

           when(event){

               is UIevents.navigateToJoinScreen -> navController.navigate(Routes.JoinCreateScreen)
               is UIevents.ShowSnackBar -> {

                   Log.d("Called Snack bar", "NameScreen: hi")
                   SnackbarHostState.showSnackbar("Name Field cannot be empty")

               }

           }



        }



    }


    DoodleFrontendTheme {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = {
                SnackbarHost(hostState = SnackbarHostState)
            }) { innerPadding ->

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

                    Text(
                        "Name",
                        fontFamily = cursiveFont,
                        fontSize = 32.sp,
                        color = Color.Black
                    )
                    Image(
                        painter = painterResource(R.drawable.cursordoodle),
                        contentDescription = "",
                        modifier = Modifier
                            .rotate(270f)
                            .size(50.dp)
                    )

                }
                DoodleTextfield(textFieldState)
                Spacer(Modifier.weight(1f))

                Box(modifier = Modifier.padding(32.dp)) {
                    button {

                        viewModel.uploadName(textFieldState.text.toString())

                    }
                }


            }

        }

    }

}



