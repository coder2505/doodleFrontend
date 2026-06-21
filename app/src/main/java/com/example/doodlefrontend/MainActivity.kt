package com.example.doodlefrontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.views.NameScreen
import com.example.doodlefrontend.views.WelcomeScreen

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

                }
            )

        }
    }
}
