package com.example.doodlefrontend.views.updatetext

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun TypographyRow(fontSelected: MutableStateFlow<FontFamily>) {
    Row() {
        listOfFonts.forEach { font ->

            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .border(
                            2.dp,
                            Color.Black,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .background(
                            if (fontSelected.collectAsState().value == font.fontFamily)
                                Color.Black else Color.White
                        )
                        .clickable(
                            onClick = {
                                fontSelected.value =
                                    font.fontFamily
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Aa",
                        fontFamily = font.fontFamily,
                        color =
                            if (fontSelected.collectAsState().value == font.fontFamily)
                                Color.White else Color.Black,
                        fontSize = 24.sp
                    )
                }
            }

        }
    }
}
