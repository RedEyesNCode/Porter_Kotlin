package com.ushatech.porter.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson

open class SimpleSession(context: Context) {
    private val PREFS_NAME =SessionKeys.PREFERENCES_NAME
    private var sharedPref: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun put(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun clear() {
        editor.clear()
            .apply()
    }
    // This AppSession needs to store object in the also. we need to implement that method.
    // Update for storing the objectS in the session as well.


    fun putObject(key: String?, obj: Any?) {
//        checkForNullKey(key)
        val gson = Gson()
        Log.i("DEV_ASHUTOSH", "putObject: SimpleSession "+ Gson().toJson(obj))
        putString(key, gson.toJson(obj))
    }


    fun putString(key: String?, value: String?) {
//        checkForNullKey(key)
//        checkForNullValue(value)
        sharedPref.edit().putString(key, value).apply()
    }

    // Adding the function to get the user details (data) from the session as well.


    fun getObject(key: String?, classOfT: Class<*>?): Any? {
        val json = getString(key!!)
        return Gson().fromJson(json, classOfT)
    }
}