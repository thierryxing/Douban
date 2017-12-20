package com.thierry.douban.model


/**
 * Created by Thierry on 2017/7/6.
 */
open class Card(
        @Transient open var layout: Int = -1,
        @Transient open val cardData: Any? = null
) : BaseModel()