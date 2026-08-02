package com.example.doodlefrontend.views.widget

import android.content.Context
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.doodlefrontend.repository.GetWidgetTextRepo
import com.example.doodlefrontend.services.GetTextWorkManager
import javax.inject.Inject
import kotlin.math.log

class WidgetReceiver() : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = Widget()

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)

        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<GetTextWorkManager>().build()

        if (context != null) {
            Log.d("from widgetReciever", "widget placed on screen")
            WorkManager.getInstance(context).enqueue(uploadWorkRequest)
        }
    }
}
