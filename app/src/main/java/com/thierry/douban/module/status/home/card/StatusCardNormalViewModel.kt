package com.thierry.douban.module.status.home.card

import com.thierry.douban.model.Timeline
import com.thierry.douban.module.common.BaseViewModel

/**
 * Created by Thierry on 2017/7/16.
 */
open class StatusCardNormalViewModel(open val timeline: Timeline) : BaseViewModel() {

    fun topViewModel(): StatusCardTopViewModel = StatusCardTopViewModel(timeline)
    fun actionViewModel(): StatusCardActionViewModel = StatusCardActionViewModel(timeline)

}