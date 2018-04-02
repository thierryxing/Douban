package com.thierry.douban.model

/**
 * Created by Thierry on 2017/7/9.
 */

/**
 * 状态时间线实体
 */
data class TimelineWrapper(
        val count: Int,
        val items: List<Timeline>,
        val new_status_count_str: String
) : BaseModel()

/**
 * 时间线实体
 */
data class Timeline(
        val status: Status,
        val type: String,
        val comments: List<StatusComment>
) : Card()

/**
 * 推荐的时间线包装实体
 */
data class TimelineRecWrapper(
        val items: List<Status>
) : BaseModel()

/**
 * 状态实体
 */
data class Status(
        val id: String,
        val liked: Boolean,
        val text: String,
        val likedCount: Int,
        val resharesCount: Int,
        val activity: String,
        val author: Author?,
        val images: List<StatusImage>,
        val createTime: String,
        val resharesStatus: Status?,
        val card: StatusCard?
) : Card()

data class Author(
        val id: String,
        val name: String,
        val avatar: String
) : BaseModel()

data class StatusImage(
        val large: Image,
        val isAnimated: Boolean,
        val normal: Image
) : BaseModel()

data class Image(
        val url: String,
        val width: Int,
        val Height: Int
) : BaseModel()

data class StatusComment(
        val author: Author,
        val text: String,
        val uri: String
) : BaseModel()

data class StatusCard(
        val rating: StatusCardRating,
        val title: String,
        val subtitle: String,
        val url: String,
        val image: StatusImage
) : BaseModel()

data class StatusCardRating(
        val count: Int,
        val max: Int,
        val starCount: Float,
        val value: Float
) : BaseModel()