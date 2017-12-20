package com.thierry.douban.model

import com.thierry.douban.util.CardLayout


/**
 * Created by Thierry on 2017/6/27.
 */
data class SettingItem(
        val title: String,
        val subTitle: String = "",
        val showRightArrow: Boolean = true,
        val isFirstItem: Boolean = false,
        override var layout: Int = CardLayout.SettingsNormal.type
) : Card()