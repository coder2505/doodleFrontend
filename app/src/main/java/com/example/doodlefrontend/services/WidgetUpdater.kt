package com.example.doodlefrontend.services

import android.content.Context
import androidx.glance.appwidget.updateAll
import com.example.doodlefrontend.views.widget.Widget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object WidgetUpdater {
    @JvmStatic
    fun update(context: Context, widget: Widget) {
        CoroutineScope(Dispatchers.IO).launch {
            widget.updateAll(context)
        }
    }
}