package com.blackhand.chatgpt.core.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences


object SharedPref {

    private var mSharedPref: SharedPreferences? = null

    fun init(context: Context) {
        if (mSharedPref == null) mSharedPref =
            context.getSharedPreferences("MyPref", Activity.MODE_PRIVATE)
    }

    fun getString(key: String?, defValue: String?): String? {
        return mSharedPref!!.getString(key, defValue)
    }

    fun putString(key: String?, value: String?) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }

    fun getInteger(key: String?, defValue: Int): Int {
        return mSharedPref!!.getInt(key, defValue)
    }

    fun putInteger(key: String?, value: Int?) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putInt(key, value!!)
        prefsEditor.apply()
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return mSharedPref!!.getBoolean(key, defValue)
    }

    fun putBoolean(key: String?, value: Boolean) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putBoolean(key, value)
        prefsEditor.apply()
    }

    fun getLong(key: String?, defValue: Long): Long {
        return mSharedPref!!.getLong(key, defValue)
    }

    fun putLong(key: String?, value: Long) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putLong(key, value)
        prefsEditor.apply()
    }

    fun getFloat(key: String?, defValue: Float): Float {
        return mSharedPref!!.getFloat(key, defValue)
    }

    fun putFloat(key: String?, value: Float) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putFloat(key, value)
        prefsEditor.apply()
    }

    fun clearPreference(context: Context?) {
        mSharedPref!!.edit().clear().apply()
    }

    fun removePreference(key: String?) {
        mSharedPref!!.edit().remove(key).apply()
    }
}