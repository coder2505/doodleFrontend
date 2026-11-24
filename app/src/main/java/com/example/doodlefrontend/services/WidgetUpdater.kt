package com.example.doodlefrontend.services

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkRequest
import com.example.doodlefrontend.views.widget.Widget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object WidgetUpdater {
    @JvmStatic
    fun update(context: Context, widget: Widget, payload: String, color : String, font : String) {
        runBlocking {

            val manager = GlanceAppWidgetManager(context)
            val glanceIds = manager.getGlanceIds(widget.javaClass)

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