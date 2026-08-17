package com.example.doodlefrontend

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.Routes.HomeScreen
import com.example.doodlefrontend.security.TokenManager
import com.example.doodlefrontend.utils.SharedPrefManager
import com.example.doodlefrontend.viewmodels.GetRoomMembers
import com.example.doodlefrontend.views.HomeScreen.HomeScreen
import com.example.doodlefrontend.views.JoinCreateRoom
import com.example.doodlefrontend.views.JoinRoom
import com.example.doodlefrontend.views.NameScreen
import com.example.doodlefrontend.views.RoomMembersScreen
import com.example.doodlefrontend.views.UpdateScreenTextScreen
import com.example.doodlefrontend.views.WelcomeScreen
import com.example.doodlefrontend.views.createroom.CreateRoom
import com.example.doodlefrontend.views.createroom.CreateRoomScreen2
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Route
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {

        SharedPrefManager.init(applicationContext)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = startScreen(),
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

                    composable(Routes.HomeScreen) {

                        HomeScreen(navController = navController)

                    }

                    composable(Routes.RoomMembersScreen) { backStackEntry ->

                        val parentEntry = remember(backStackEntry) {
                            navController.getBackStackEntry(HomeScreen)
                        }

                        val getRoomMembersViewModel: GetRoomMembers = hiltViewModel(parentEntry)

                        val membersList by getRoomMembersViewModel.listMembers.collectAsState()

                        RoomMembersScreen(listMembers = membersList)
                    }

                    composable(Routes.UpdateTextScreen) {

                        UpdateScreenTextScreen()

                    }
                }
            )

        }
    }

    fun startScreen(): String {
        return if (tokenManager.getAccessToken() != null) {
            Log.d("hi", tokenManager.getAccessToken().toString())
            Routes.HomeScreen
        } else {
            Routes.WelcomeScreen
        }
    }
}
