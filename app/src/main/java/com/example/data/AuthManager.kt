package com.example.data

import android.content.Context
import android.content.SharedPreferences

class AuthManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    
    fun isMasterPasswordSet(): Boolean {
        return prefs.contains("master_password")
    }
    
    fun getMasterPassword(): String? {
        return prefs.getString("master_password", null)
    }
    
    fun setMasterPassword(password: String) {
        prefs.edit().putString("master_password", password).apply()
    }
}
