package com.example.doodlefrontend.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.Provides

/**
 * Global SharedPreferences Manager to get and set shared preference key-value pairs.
 */
object SharedPrefManager {

    private const val PREF_NAME = "global_doodle_prefs"
    private const val KEY_TEXT = "text"

    private var sharedPreferences: SharedPreferences? = null

    /**
     * Initializes the global SharedPreferences instance.
     * Recommended to call this inside Application.onCreate().
     */
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

    // =========================================================================
    // Get and Set methods for "text" key (Context-aware)
    // =========================================================================

    /**
     * Sets the "text" string value in SharedPreferences using Context.
     */
    fun setText(context: Context, text: String) {
        getPrefs(context).edit { putString(KEY_TEXT, text) }
    }

    /**
     * Gets the "text" string value from SharedPreferences using Context.
     * @param defaultValue Default value if key is not found (default: "")
     */
    fun getText(context: Context, defaultValue: String = ""): String {
        return getPrefs(context).getString(KEY_TEXT, defaultValue) ?: defaultValue
    }

    // =========================================================================
    // Get and Set methods for "text" key (Global instance after init)
    // =========================================================================

    /**
     * Sets the "text" string value in SharedPreferences.
     * Note: Requires SharedPrefManager.init(context) to be called previously.
     */
    fun setText(text: String) {
        val prefs = sharedPreferences ?: throw IllegalStateException(
            "SharedPrefManager is not initialized. Call SharedPrefManager.init(context) or pass Context."
        )
        prefs.edit { putString(KEY_TEXT, text) }
    }

    /**
     * Gets the "text" string value from SharedPreferences.
     * Note: Requires SharedPrefManager.init(context) to be called previously.
     * @param defaultValue Default value if key is not found (default: "")
     */
    fun getText(defaultValue: String = ""): String {
        val prefs = sharedPreferences ?: throw IllegalStateException(
            "SharedPrefManager is not initialized. Call SharedPrefManager.init(context) or pass Context."
        )
        return prefs.getString(KEY_TEXT, defaultValue) ?: defaultValue
    }

}
