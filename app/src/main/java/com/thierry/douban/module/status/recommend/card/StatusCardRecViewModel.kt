package com.thierry.douban.module.status.recommend.card

import com.thierry.douban.model.Status
import com.thierry.douban.module.common.BaseViewModel

/**
 * Created by Thierry on 2017/7/16.
 */
open class StatusCardRecViewModel(open val status: Status) : BaseViewModel() {
    fun authorAvatarUrl(): String = status.author?.avatar ?: ""

    fun statusText(): String = status.text

    fun authorNameText(): String = status.author?.name ?: ""

    fun activityText(): String = status.activity

    fun textHidden(): Boolean = statusText().isEmpty()
}