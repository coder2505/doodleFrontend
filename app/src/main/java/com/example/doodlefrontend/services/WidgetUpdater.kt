package com.example.doodlefrontend.services

import android.content.Context
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
    fun update(context: Context, widget: Widget) {
        runBlocking {
            widget.updateAll(context)
        }
    }
}