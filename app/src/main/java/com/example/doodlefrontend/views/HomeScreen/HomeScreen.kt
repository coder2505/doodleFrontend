package com.example.doodlefrontend.views.HomeScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doodlefrontend.R
import com.example.doodlefrontend.Routes
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import com.example.doodlefrontend.ui.theme.cursiveFont
import com.example.doodlefrontend.ui.theme.notcursiveFont
import com.example.doodlefrontend.utils.SharedPrefManager
import com.example.doodlefrontend.viewmodels.GetRoomDetailsViewModel
import com.example.doodlefrontend.viewmodels.UpdateTextViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen(
    updateTextViewModel: UpdateTextViewModel = hiltViewModel(),
    getRoomDetails: GetRoomDetailsViewModel = hiltViewModel(),
    navController: NavController = rememberNavController()
) {

    var text = updateTextViewModel.doodleText.collectAsState("")
    var roomName = getRoomDetails.roomName.collectAsState()
    val color = SharedPrefManager.parseColor(SharedPrefManager.getColor())

    Log.d("hi", "HomeScreen: ${color}")

    DoodleFrontendTheme {

        Scaffold(Modifier.fillMaxSize()) { padding ->


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 8.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        roomName.value,
                        fontFamily = cursiveFont,
                        fontSize = 32.sp,
                        color = Color.Black
                    )
                    Spacer(Modifier.weight(1f))
                    ProfileButton(navController)
                }

                Spacer(modifier = Modifier.weight(1f))


                Text(
                    "Current Doodle",
                    fontFamily = notcursiveFont,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Box(
                    contentAlignment = Alignment.Center
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.6f)
                            .padding(8.dp)
                            .border(
                                2.dp,
                                Color.Black,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .background(color = color)
                            .clickable(
                                onClick = {
                                    navController.navigate(Routes.UpdateTextScreen)
                                }
                            ),
                        contentAlignment = Alignment.Center,
                    ) {

                        Text(
                            text.value,
                            fontFamily = cursiveFont,
                            color = Color.Black,
                            fontSize = 40.sp
                        )

                    }

//
//                    Image(
//                        modifier = Modifier
//                            .clickable(
//                                onClick = {
//                                    navController.navigate(Routes.UpdateTextScreen)
//                                }
//                            ),
//                        contentDescription = "",
//                        painter = painterResource(R.drawable.doodlebackground)
//                    )

                    Text(
                        text.value,
                        fontFamily = cursiveFont,
                        color = Color.Black,
                        fontSize = 40.sp
                    )
                }
                Text(
                    "User123",
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = notcursiveFont,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                    color = Color.Black
                )


                Spacer(modifier = Modifier.weight(1f))


            }

//            if (openBottomSheet) {
//
//
//                ModalSheet(
//                    bottomSheetState
//                ) {
//                    openBottomSheet = false
//                }
//
//
//            }


        }


    }

}

@Composable
fun ProfileButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate(Routes.RoomMembersScreen)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .height(60.dp)
            .paint(
                painter = painterResource(R.drawable.iconbutton),
            ),
    ) {
        Text(
            "m",
            color = Color.Black,
            fontFamily = cursiveFont,
            fontSize = 24.sp
        )

    }
}


sealed class HomeScreen {

    class ShowSnackBar(val message: String) : HomeScreen()
    object Success : HomeScreen()

}