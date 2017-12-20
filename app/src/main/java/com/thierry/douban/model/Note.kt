package com.thierry.douban.model

/**
 * Created by Thierry on 2017/7/12.
 */
data class Note(
        val content: String,
        val liked: Boolean,
        val donateCount: Int,
        val isDonated: Boolean,
        val likersCount: Int,
        val commentsCount: Int,
        val typeName: String = ""
) : BaseModel()