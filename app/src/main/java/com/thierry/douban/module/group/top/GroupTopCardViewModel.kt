package com.thierry.douban.module.group.top

import android.graphics.drawable.Drawable
import com.thierry.douban.model.SimpleItem
import com.thierry.douban.module.common.BaseViewModel
import com.thierry.douban.util.ResUtils

/**
 * Created by Thierry on 2017/7/14.
 */
class GroupTopCardViewModel(var items: List<SimpleItem>) : BaseViewModel() {

    fun image(position: Int) = ResUtils.getDrawableByName("ic_${items[position].name}_group_topics")

    fun title(position: Int) = items[position].title

}