package com.thierry.douban.util

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import com.thierry.douban.App

/**
 * Created by Thierry on 2017/7/14.
 */
object VersionUtils {

    private val context = App.instance
    private val packageManager = context.packageManager

    fun getVersionCode(): String {
        return try {
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            "${packageInfo.versionCode}"
        } catch (e: Exception) {
            ""
        }
    }

    fun getVersionName(): String {
        return try {
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName
        } catch (e: Exception) {
            ""
        }
    }

}