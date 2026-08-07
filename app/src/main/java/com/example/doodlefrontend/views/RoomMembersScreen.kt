package com.example.doodlefrontend.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import com.example.doodlefrontend.ui.theme.notcursiveFont

@Preview
@Composable
fun RoomMembersScreen(
    listMembers : List<String> = listOf("manish", "anushka", "jagan", "yukta")
) {
    
    DoodleFrontendTheme() {

        Scaffold(modifier = Modifier.fillMaxSize()) { contentPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp)
                    .padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                
                    Text(
                        "502",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = notcursiveFont,
                            fontSize = 96.sp,
                        )
                    )

                    Text(
                        "room code",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = notcursiveFont,
                            fontSize = 32.sp
                        )
                    )

                Spacer(Modifier.height(64.dp))

                
                LazyColumn(
                    modifier = Modifier.padding(32.dp)
                ) {
                    // Add 5 items
                    items(listMembers.size) { index ->
                        val itemShape = when (index) {
                            0 -> RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 16.dp
                            )
                            listMembers.lastIndex -> RoundedCornerShape(
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            )
                            else -> RectangleShape
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(itemShape)
                                .background(Color(0xffF5E3AF))
                                .padding(16.dp)
                        ) {
                            Text(
                                listMembers[index],
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxSize(),
                                fontFamily = notcursiveFont,
                                fontWeight = FontWeight.Black,
                                color = Color.Black
                            )
                        }

                        if (index < listMembers.lastIndex) {
                            HorizontalDivider(
                                color = Color.Black,
                                thickness = 1.dp
                            )
                        }
                    }

                }



            }


        }


    }


}