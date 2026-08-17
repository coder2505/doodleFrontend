package com.example.doodlefrontend.views.updatetext

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun PreviewWidget(
    width: Int,
    font: MutableStateFlow<FontFamily>,
    color: MutableStateFlow<Color>,
    textFieldState: TextFieldState,
) {
    Box(
        modifier = Modifier
            .size(width.dp)
            .border(
                color = Color.Black,
                width = 2.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = color.collectAsState().value,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {

        EditableTextField(textFieldState, font)

    }
}
