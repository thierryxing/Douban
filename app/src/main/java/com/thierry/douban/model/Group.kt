package com.thierry.douban.model

/**
 * Created by Thierry on 2017/7/6.
 */

data class GroupWrapper(
        val count: Int,
        val start: Int,
        val total: Int,
        val groups: List<Group>
) : BaseModel()

data class Group(
        val name: String,
        val unreadCountStr: String,
        val avatar: String?
) : Card()

data class MixRecGroupWrapper(
        val mixedRecGroups: List<Promo>
) : BaseModel()