package com.thierry.douban.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.thierry.douban.extension.loadRound
import com.thierry.douban.extension.loadUrl

/**
 * Created by Thierry on 2017/7/14.
 */
/**
 * 重点知识，bind adapter，静态方法
 */
@BindingAdapter("bind:image")
fun loadUrl(view: ImageView, url: String) {
    view.loadUrl(url)
}

@BindingAdapter("bind:roundImage")
fun loadRound(view: ImageView, url: String) {
    view.loadRound(url)
}

@BindingAdapter("android:layout_height")
fun setLayoutHeight(view: ImageView, height: Float) {
    val layoutParams = view.layoutParams
    layoutParams.height = height.toInt()
    view.layoutParams = layoutParams
}

/**
 * 重点知识，bind adapter，静态方法
 * default=wrap_content
 */
@BindingAdapter("android:layout_width")
fun setLayoutWidth(view: ImageView, width: Float) {
    val layoutParams = view.layoutParams
    layoutParams.width = width.toInt()
    view.layoutParams = layoutParams
}