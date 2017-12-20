package com.thierry.douban.module.status.home.card

import android.text.format.DateUtils
import com.thierry.douban.model.Timeline
import com.thierry.douban.module.common.BaseViewModel
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatterBuilder
import java.util.*


/**
 * Created by Thierry on 2017/7/16.
 */
class StatusCardTopViewModel(val timeline: Timeline) : BaseViewModel() {

    fun authorAvatarUrl(): String = timeline.status.author?.avatar ?: ""

    fun statusText(): String = timeline.status.text

    fun createTimeText(): String {
        val parser = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parser
        val formatter = DateTimeFormatterBuilder().append(parser).toFormatter()
        val dateTime = formatter.parseDateTime(timeline.status.createTime)
        val time = dateTime.millis
        return DateUtils.getRelativeTimeSpanString(time, Date().time, DateUtils.MINUTE_IN_MILLIS).toString()
    }

    fun authorNameText(): String = timeline.status.author?.name ?: ""

    fun activityText(): String = timeline.status.activity

    fun textHidden(): Boolean = statusText().isEmpty()

}