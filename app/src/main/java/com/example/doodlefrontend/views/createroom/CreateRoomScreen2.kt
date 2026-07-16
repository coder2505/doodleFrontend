package com.example.doodlefrontend.views.createroom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.Routes
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import com.example.doodlefrontend.ui.theme.cursiveFont
import com.example.doodlefrontend.ui.theme.notcursiveFont
import com.example.doodlefrontend.viewmodels.CreateRoomViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



@Composable
@Preview
fun CreateRoomScreen2(
    navController: NavController = rememberNavController(),
    createRoomViewModel: CreateRoomViewModel = hiltViewModel()
){

    val roomCode by createRoomViewModel.uiState.collectAsStateWithLifecycle()

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
                Text("your room code", fontFamily = cursiveFont, fontSize = 20.sp, color = Color.Black)
                Text(roomCode, fontFamily = notcursiveFont, fontSize = 64.sp, color = Color.Black)

                Spacer(modifier = Modifier.weight(1f))
                Box {

                    Column() {

                        doodleButton {

                            navController.navigate(Routes.HomeScreen)

                        }

                    }

                }


            }

        }

    }




}