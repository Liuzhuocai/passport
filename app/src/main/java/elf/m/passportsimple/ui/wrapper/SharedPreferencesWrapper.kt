package elf.m.passportsimple.ui.wrapper

import android.content.SharedPreferences
import com.google.gson.Gson

interface IPrefs {
    fun put(key: String, value: Any)
    fun <T:Any> get(key: String, defaultValue: T):T
}

fun SharedPreferences.wrapper() =
    object: Prefs() {
        override fun prefs() = this@wrapper
    }

abstract class Prefs: IPrefs {
    private val gson by lazy { Gson() }

    abstract fun prefs(): SharedPreferences

    override fun put(key: String, value: Any) {
        prefs().edit().apply {
            when(value) {
                is Boolean-> putBoolean(key, value)
                is Int-> putInt(key, value)
                is Long-> putLong(key, value)
                is Float-> putFloat(key, value)
                is String-> putString(key, value)
                else-> putString(key, gson.toJson(value))
            }
        }.apply()
    }

    override fun <T:Any> get(key: String, defaultValue: T):T =
        with(prefs()) {
            when (defaultValue) {
                is Boolean -> getBoolean(key, defaultValue) as T
                is Int -> getInt(key, defaultValue) as T
                is Long -> getLong(key, defaultValue) as T
                is Float -> getFloat(key, defaultValue) as T
                is String -> getString(key, defaultValue) as T
                else -> with(getString(key, null)) {
                    if (this == null) {
                        defaultValue
                    } else {
                        gson.fromJson(this, defaultValue::class.java)
                    }
                }
            }
        }


}