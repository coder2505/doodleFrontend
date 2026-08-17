package com.example.doodlefrontend.views.updatetext

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.doodlefrontend.ui.theme.notcursiveFont

@Composable
fun Header() {
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
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Done", style = TextStyle(color = Color.White))
        }
    }
}
