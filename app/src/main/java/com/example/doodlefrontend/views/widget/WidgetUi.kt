package com.example.doodlefrontend.views.widget

import android.content.ComponentName
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.datastore.preferences.core.Preferences
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
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
import com.example.doodlefrontend.MainActivity
import com.example.doodlefrontend.utils.SharedPrefManager
import javax.inject.Inject


@Composable
fun WidgetContent(
    componentName: ComponentName,
    text: String,
    color : String,
    font: String
) {
    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .appWidgetBackground()
            .background(
                Color(color.toColorInt())
            )
            .cornerRadius(16.dp)
            .padding(16.dp).clickable(
                actionStartActivity(
                    componentName = componentName
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
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
