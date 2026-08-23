package com.example.doodlefrontend.views.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.doodlefrontend.services.GetTextWorkManager


class WidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = Widget()

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        context?.let { enqueueWork(it) }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        enqueueWork(context)
    }

    private fun enqueueWork(context: Context) {
        val request = OneTimeWorkRequestBuilder<GetTextWorkManager>().build()
        WorkManager.getInstance(context).enqueue(request)
    }
}
