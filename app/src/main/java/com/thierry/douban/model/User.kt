package com.thierry.douban.model

/**
 * Created by Thierry on 2017/7/8.
 */
data class User(
        val uid: String = "",
        val followingCount: Int = 0,
        val followersCount: Int = 0,
        val name: String = "",
        val largeAvatar: String?
) : BaseModel()