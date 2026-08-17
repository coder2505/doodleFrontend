package com.example.doodlefrontend.views.updatetext

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun BackgroundColorRow(
    color: MutableStateFlow<Color>
) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        listOfColor.forEach { colorOption ->
            Column(
                modifier = Modifier.padding(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .background(colorOption.colorOption)
                        .clickable(
                            onClick = {
                                color.value = colorOption.colorOption
                            }
                        )
                )

            }
        }
    }
}
