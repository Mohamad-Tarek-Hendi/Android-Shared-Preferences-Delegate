package com.example.androidsharedpreferencesdelegate

import android.content.Context
import androidx.activity.ComponentActivity
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPreferenceDelegate<T>(
    private val context: Context,
    private val name: String,
    private val defaultValue: T
) : ReadWriteProperty<Any?, T> {

    private val sharedPreferences by lazy {
        context.getSharedPreferences("my_prefs", ComponentActivity.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        @Suppress("UNCHECKED_CAST")
        return sharedPreferences.all[name] as? T ?: defaultValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        with(sharedPreferences.edit()) {
            when (value) {
                is String -> putString(name, value)
                is Int -> putInt(name, value)
                is Long -> putLong(name, value)
                is Float -> putFloat(name, value)
                is Boolean -> putBoolean(name, value)
                else -> throw IllegalArgumentException("Unsupported type")
            }
            apply()
        }
    }
}

fun <T> Context.sharedPreferences(name: String, defaultValue: T) =
    SharedPreferenceDelegate(this, name, defaultValue)