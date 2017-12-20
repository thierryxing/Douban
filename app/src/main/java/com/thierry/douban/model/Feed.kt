package com.thierry.douban.model

/**
 * Created by Thierry on 2017/7/2.
 */
data class FeedWrapper(
        val date: String,
        val toast: String,
        val recommendFeeds: List<Feed>
) : BaseModel()

data class Feed(
        val id: String = "",
        val sourceCn: String = "",
        val target: FeedTarget? = null,
        val title: String = "",
        val card: FeedCard? = null,
        override var layout: Int
) : Card()

data class FeedAuthor(
        val avatar: String,
        val name: String,
        val verifyType: Int
) : BaseModel()

data class FeedTarget(
        val id: String,
        val kind: Int,
        val author: FeedAuthor?,
        val url: String,
        val uri: String,
        val coverUrl: String?,
        val desc: String
) : BaseModel()

data class FeedCard(
        val name: String
) : BaseModel()