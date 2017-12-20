package com.thierry.douban.module.profile.menu

import android.graphics.drawable.Drawable
import com.thierry.douban.model.SimpleItem
import com.thierry.douban.module.common.BaseViewModel
import com.thierry.douban.util.ResUtils

/**
 * Created by Thierry on 2017/7/15.
 */
class ProfileMenuItemViewModel(val item: SimpleItem) : BaseViewModel() {

    fun menuTitleText(): String = item.title

    fun menuImage(): Drawable = ResUtils.getDrawableByName("ic_my_${item.name}")

}