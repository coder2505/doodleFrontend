package com.example.doodlefrontend.views.updatetext

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.doodlefrontend.ui.theme.notcursiveFont
import com.example.doodlefrontend.viewmodels.UpdateTextViewModel

@Composable
fun Header(
    updateTextViewModel: UpdateTextViewModel = hiltViewModel(),
    textFieldState: TextFieldState,
    color: Color,
    font: FontFamily
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Update Text",
            fontFamily = notcursiveFont,
            fontSize = 24.sp,
            color = Color.Black
        )
        Button(
            onClick = {
                updateTextViewModel.update(
                    payload = textFieldState.text.toString(),
                    color = color.toString(),
                    font = font.toString()
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Done", style = TextStyle(color = Color.White))
        }
    }
}
