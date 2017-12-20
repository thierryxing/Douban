package com.thierry.douban.module.profile.setting

import com.thierry.douban.model.*
import com.thierry.douban.module.common.BaseViewModel

/**
 * Created by Thierry on 2017/7/13.
 */
class SettingCardViewModel(val item: SettingItem) : BaseViewModel() {

    fun titleText() = item.title

    fun subTitleText() = item.subTitle

    fun isFirstElement() = item.isFirstItem

    fun showRightArrow() = item.showRightArrow

}