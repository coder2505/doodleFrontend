package com.example.doodlefrontend.services

import android.content.Context
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import com.example.doodlefrontend.views.widget.Widget
import kotlinx.coroutines.runBlocking
import androidx.compose.ui.graphics.toArgb

object WidgetUpdater {
    @JvmStatic
    fun update(context: Context, widget: Widget, payload: String, color : String, font : String) {
        runBlocking {

            val manager = GlanceAppWidgetManager(context)
            val glanceIds = manager.getGlanceIds(widget.javaClass)

            Log.d("inside", "color: ${color}")
//            val hexColor = String.format("#%08X", color.toArgb())

            glanceIds.forEach { glanceId ->
                updateAppWidgetState(context, glanceId) { prefs ->
                    prefs[Widget.KEY_PAYLOAD] = payload
                    prefs[Widget.KEY_COLOR] = color
                    prefs[Widget.KEY_FONT] = font
                }
                widget.update(context, glanceId)
            }

        }
    }
}