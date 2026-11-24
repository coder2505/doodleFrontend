package com.example.doodlefrontend.views.widget

import android.content.ComponentName
import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import javax.inject.Inject

class Widget @Inject constructor(): GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {

            val componentName  = ComponentName(
                context.packageName,
                "com.example.doodlefrontend.MainActivity"
            )

            WidgetContent(componentName)
        }
    }
}
