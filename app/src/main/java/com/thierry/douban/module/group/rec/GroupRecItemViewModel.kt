package com.thierry.douban.module.group.rec

import com.thierry.douban.model.Promo
import com.thierry.douban.module.common.BaseViewModel

/**
 * Created by Thierry on 2017/7/14.
 */
class GroupRecItemViewModel(var promo: Promo) : BaseViewModel() {

    fun imageUrl(): String = promo.image

}