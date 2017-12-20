package com.thierry.douban.module.profile.top

import com.thierry.douban.model.User
import com.thierry.douban.module.common.BaseViewModel

/**
 * Created by Thierry on 2017/7/15.
 */
class ProfileTopCardViewModel(val user: User?) : BaseViewModel() {

    fun profileFollowedText(): String = "被关注 ${user?.followersCount}"

    fun profileFollowingText(): String = "关注 ${user?.followingCount}"

    fun profileNameText(): String = user?.name ?: ""

    fun profileIDText(): String = "ID: ${user?.uid}"

    fun avatarUrl(): String = user?.largeAvatar ?: ""

}