package com.thierry.douban.extension

/**
 * Created by Thierry on 2017/7/12.
 */
import android.app.Fragment
import org.jetbrains.anko.bundleOf

inline fun <reified T : Fragment> instanceOf(vararg params: Pair<String, Any>)
        = T::class.java.newInstance().apply {
    arguments = bundleOf(*params)
}