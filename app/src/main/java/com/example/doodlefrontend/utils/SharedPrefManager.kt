package com.example.doodlefrontend.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.graphics.Color
import androidx.core.content.edit
import androidx.core.graphics.toColorInt

object SharedPrefManager {

    private const val PREF_NAME = "global_doodle_prefs"
    private const val KEY_TEXT = "text"
    private const val KEY_COLOR = "color"
    private const val KEY_FONT = "font"

    const val DEFAULT_COLOR: String = "#ffffff"

    private var sharedPreferences: SharedPreferences? = null

    fun init(context: Context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.applicationContext
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }
    }

    private fun prefs(): SharedPreferences =
        sharedPreferences ?: throw IllegalStateException(
            "SharedPrefManager is not initialized. Call SharedPrefManager.init(context) first."
        )

    fun setText(text: String) = prefs().edit { putString(KEY_TEXT, text) }
    fun getText(defaultValue: String = ""): String =
        prefs().getString(KEY_TEXT, defaultValue) ?: defaultValue

    fun setColor(color: String) = prefs().edit { putString(KEY_COLOR, color) }
    fun getColor(defaultValue: String = DEFAULT_COLOR): String =
        prefs().getString(KEY_COLOR, defaultValue) ?: defaultValue

    /** Parses an ARGB integer string or `#hex` color string, falling back safely if invalid. */
    fun parseColor(rawColor: String, fallback: Color = Color.White): Color =
        rawColor.toIntOrNull()?.let { Color(it) }
            ?: runCatching { Color(rawColor.toColorInt()) }.getOrDefault(fallback)

    fun setFont(font: String) = prefs().edit { putString(KEY_FONT, font) }
    fun getFont(defaultValue: String = ""): String =
        prefs().getString(KEY_FONT, defaultValue) ?: defaultValue
}