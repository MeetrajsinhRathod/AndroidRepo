package com.example.onecloud.Utills

import android.content.Context
import android.content.SharedPreferences

object SharedPrefHelper {

    var prefName = "application"
    var userToken = "userToken"
    var userId = "userId"
    var isUserLoggedIn = "isUserLoggedIn"

    /** Used to get the sharedPreferences object for given prefName */
    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    /** Used to save the token and userId in SharedPreferences*/
    fun saveTokenAndId(context: Context, token: String?, id: String?) {
        val sharedPref = getSharedPreferences(context)
        val editor = sharedPref.edit()
        editor.putString(userToken, token)
        editor.putString(userId, id)
        editor.apply()
    }

    /** Used to get the token back from SharedPreferences */
    fun getToken(context: Context): String {
        val sharedPref = getSharedPreferences(context)
        val token = sharedPref.getString(userToken, null)
        return token ?: ""
    }

    /** Used to get the userId back from SharedPreferences */
    fun getId(context: Context): String {
        val sharedPref = getSharedPreferences(context)
        val userId = sharedPref.getString(userId, null)
        return userId ?: ""
    }

    /** Used to update the login Status of the user */
    fun updateLoginStatus(context: Context, isLoggedIn: Boolean) {
        val sharedPref = getSharedPreferences(context)
        val editor = sharedPref.edit()
        editor.putBoolean(isUserLoggedIn, isLoggedIn)
        editor.apply()
    }

    /** Used to check if user is currently logged in */
    fun isLoggedIn(context: Context): Boolean {
        val sharedPref = getSharedPreferences(context)
        return sharedPref.getBoolean(isUserLoggedIn, false)
    }
}