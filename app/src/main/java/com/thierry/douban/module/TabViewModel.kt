package com.thierry.douban.module

import com.thierry.douban.model.SimpleItem
import com.thierry.douban.module.common.BaseViewModel

/**
 * Created by Thierry on 2017/7/22.
 */
class TabViewModel : BaseViewModel() {
    val tabItems = arrayListOf(
            SimpleItem("首页", "home"),
            SimpleItem("书影音", "subject"),
            SimpleItem("广播", "status"),
            SimpleItem("小组", "group"),
            SimpleItem("我的", "profile")
    )
}