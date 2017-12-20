package com.thierry.douban.model

/**
 * Created by Thierry on 2017/7/5.
 */
data class Promo(
        val id: String,
        val uri: String,
        val image: String,
        val text: String
) : Card()