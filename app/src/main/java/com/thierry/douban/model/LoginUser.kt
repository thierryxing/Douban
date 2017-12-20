package com.thierry.douban.model

/**
 * Created by Thierry on 2017/7/22.
 */
data class LoginUser(
        val accessToken: String,
        val doubanUserName: String,
        val doubanUserId: String,
        val expiresIn: Int,
        val refreshToken: String
)