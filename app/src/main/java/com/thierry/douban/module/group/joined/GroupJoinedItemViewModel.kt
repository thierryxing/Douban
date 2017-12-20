package com.thierry.douban.module.group.joined

import com.thierry.douban.model.Group
import com.thierry.douban.module.common.BaseViewModel

/**
 * Created by Thierry on 2017/7/14.
 */
class GroupJoinedItemViewModel(var group: Group) : BaseViewModel() {

    fun imageUrl(): String = group.avatar ?: ""

    fun titleText(): String = group.name

    fun updateText(): String {
        if (group.unreadCountStr != "0") {
            return "${group.unreadCountStr}条更新"
        } else {
            return ""
        }
    }
}