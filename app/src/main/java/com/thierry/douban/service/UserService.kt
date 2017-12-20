package com.thierry.douban.service

import com.thierry.douban.model.LoginUser
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thierry.douban.App


/**
 * Created by Thierry on 2017/6/24.
 */
class UserService private constructor() {

    private val LOGIN_USER_KEY = "loginUser"
    private val cache: SharedPreferences = App.instance.getSharedPreferences("cache", MODE_PRIVATE)

    private object Holder {
        val INSTANCE = UserService()
    }

    companion object {
        fun instance() = Holder.INSTANCE
    }

    fun saveUser(json: String?) =
            cache.edit().putString(LOGIN_USER_KEY, json).commit()

    fun fetchUser(): LoginUser? {
        val json = cache.getString(LOGIN_USER_KEY, "")
        if (json == "") {
            return null
        }
        var user = try {
            gson().fromJson<LoginUser>(json)
        } catch (e: Exception) {
            null
        }
        Log.d("UserService", "user:$user")
        return user
    }

    fun deleteUser() =
            cache.edit().remove(LOGIN_USER_KEY).apply()

    private fun gson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    }

    fun isLoggedIn() = fetchUser() != null
}