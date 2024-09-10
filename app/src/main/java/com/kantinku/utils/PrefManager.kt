package com.kantinku.utils

import android.content.Context
import android.content.SharedPreferences

const val EMAIL = "email"
const val USERNAME = "username"

class PrefManager(var context: Context) {
    private val privateMode = 0
    
    private val prefName = "SharedPref"
    private val isLogin = "IsLoggedIn"
    
    private var pref: SharedPreferences = context.getSharedPreferences(prefName, privateMode)
    private var editor: SharedPreferences.Editor = pref.edit()
    
    fun setLogin(isLoggedIn: Boolean) {
        editor.putBoolean(isLogin, isLoggedIn)
        editor.commit()
    }
    
    fun setUsername(username: String) {
        editor.putString(USERNAME, username)
        editor.commit()
    }
    
    fun clear() {
        editor.clear()
        editor.commit()
    }
    
    fun setEmail(email: String) {
        editor.putString(EMAIL, email)
        editor.commit()
    }
    
    fun isLogin(): Boolean {
        return pref.getBoolean(isLogin, false)
    }
    
    fun setNew(new: Boolean) {
        editor.putBoolean("new", new)
        editor.commit()
    }
    
    fun isNew(): Boolean {
        return pref.getBoolean("new", true)
    }
}