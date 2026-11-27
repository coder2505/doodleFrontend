package com.example.doodlefrontend.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.Provides

object SharedPrefManager {

    private const val PREF_NAME = "global_doodle_prefs"
    private const val KEY_TEXT = "text"

    private var sharedPreferences: SharedPreferences? = null

    fun init(context: Context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }
    }

    private fun getPrefs(context: Context): SharedPreferences {
        return sharedPreferences ?: context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).also {
            sharedPreferences = it
        }
    }

    fun setText(context: Context, text: String) {
        getPrefs(context).edit(commit = true) { putString(KEY_TEXT, text) }
    }

    fun getText(context: Context, defaultValue: String = ""): String {
        return getPrefs(context).getString(KEY_TEXT, defaultValue) ?: defaultValue
    }

    fun setText(text: String) {
        val prefs = sharedPreferences ?: throw IllegalStateException(
            "SharedPrefManager is not initialized. Call SharedPrefManager.init(context) or pass Context."
        )
        prefs.edit(commit = true) { putString(KEY_TEXT, text) }
    }

    fun getText(defaultValue: String = ""): String {
        val prefs = sharedPreferences ?: throw IllegalStateException(
            "SharedPrefManager is not initialized. Call SharedPrefManager.init(context) or pass Context."
        )
        return prefs.getString(KEY_TEXT, defaultValue) ?: defaultValue
    }

}
