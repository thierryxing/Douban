package com.thierry.douban.util

import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import com.thierry.douban.App

/**
 * Created by Thierry on 2017/7/14.
 */
object ResUtils {

    fun getDimen(@DimenRes id: Int): Float
            = App.instance.resources.getDimension(id)

    fun getDrawable(@DrawableRes id: Int): Drawable
            = App.instance.resources.getDrawable(id, App.instance.theme)

    fun getColor(@ColorRes id: Int): Int
            = App.instance.resources.getColor(id)

    fun getDrawableByName(name: String): Drawable
            = getDrawable(App.instance.resources.getIdentifier(name, "drawable", App.instance.packageName))
}