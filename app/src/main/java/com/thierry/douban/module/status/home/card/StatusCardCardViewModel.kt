package com.thierry.douban.module.status.home.card

import com.thierry.douban.model.Timeline

/**
 * Created by Thierry on 2017/7/16.
 */
class StatusCardCardViewModel(override val timeline: Timeline) : StatusCardNormalViewModel(timeline) {

    fun imageUrl(): String = timeline.status.card?.image?.large?.url ?: ""

    fun ratingText(): String = timeline.status.card?.rating?.value?.toString() ?: ""

    fun titleText(): String = timeline.status.card?.title ?: ""

    fun subTitleText(): String = timeline.status.card?.subtitle ?: ""

}