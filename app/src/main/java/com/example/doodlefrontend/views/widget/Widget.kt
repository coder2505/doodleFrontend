package com.example.doodlefrontend.views.widget

import android.content.ComponentName
import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.currentState
import javax.inject.Inject

class Widget @Inject constructor(): GlanceAppWidget() {

    companion object{

        val KEY_PAYLOAD = stringPreferencesKey("payload")
        val KEY_COLOR = stringPreferencesKey("color")
        val KEY_FONT = stringPreferencesKey("font")

    }


    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {

            val componentName  = ComponentName(
                context.packageName,
                "com.example.doodlefrontend.MainActivity"
            )

            val prefs = currentState<Preferences>()
            val payload = prefs[KEY_PAYLOAD] ?: "Waiting for doodle"
            val color = prefs[KEY_COLOR] ?: "#800522"
            val font = prefs[KEY_FONT] ?: "default"

            Log.d("shutup", "provideGlance: "+ color)


            WidgetContent(componentName, text = payload, color = color, font = font)
        }
    }
}
