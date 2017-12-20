package com.thierry.douban.component

import android.content.Context
import android.support.v7.widget.LinearLayoutManager

/**
 * Created by Thierry on 2017/7/14.
 */
abstract class CardViewModel : RecyclerViewModel() {

    override fun handelJSON(json: String?) {}

    open fun getLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

}