package com.example.doodlefrontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.views.createroom.CreateRoom
import com.example.doodlefrontend.views.JoinCreateRoom
import com.example.doodlefrontend.views.JoinRoom
import com.example.doodlefrontend.views.NameScreen
import com.example.doodlefrontend.views.WelcomeScreen
import com.example.doodlefrontend.views.createroom.CreateRoomScreen2
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Routes.WelcomeScreen,
                builder = {

                    composable(Routes.WelcomeScreen) {
                        WelcomeScreen(navController)
                    }

                    composable(Routes.NameScreen) {
                        NameScreen(navController)
                    }
                    composable(Routes.JoinCreateScreen) {
                        JoinCreateRoom(navController)
                    }

                    composable(Routes.JoinRoomScreen) {
                        JoinRoom(navController)
                    }
                    composable(Routes.CreateRoomScreen) { backStackEntry ->
                        CreateRoom(
                            navController = navController,
                            createRoomViewModel = hiltViewModel(backStackEntry)
                        )
                    }

                    composable(Routes.CreateRoomScreen2) { backStackEntry ->

                        val parentEntry = remember(backStackEntry) {
                            navController.getBackStackEntry(Routes.CreateRoomScreen)
                        }

                        CreateRoomScreen2(
                            navController = navController,
                            createRoomViewModel = hiltViewModel(parentEntry)
                        )
                    }

                }
            )

        }
    }
}
