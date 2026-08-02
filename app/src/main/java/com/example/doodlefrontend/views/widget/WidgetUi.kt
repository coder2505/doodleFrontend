package com.example.doodlefrontend.views.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.color.ColorProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.doodlefrontend.utils.SharedPrefManager
import javax.inject.Inject


@Composable
fun WidgetContent() {
    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .appWidgetBackground()
            .background(
                ColorProvider(
                    day = Color(0xFF6750A4),
                    night = Color(0xFFD0BCFF)
                )
            )
            .cornerRadius(16.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = SharedPrefManager.getText(),
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = ColorProvider(
                    day = Color.White,
                    night = Color(0xFF381E72)
                )
            )
        )
    }
}
