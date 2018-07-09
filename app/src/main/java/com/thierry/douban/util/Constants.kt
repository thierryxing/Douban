package com.thierry.douban.util

import com.thierry.douban.model.User

/**
 * Created by Thierry on 2017/6/25.
 */

object Constants {

    const val Host = "https://frodo.douban.com"
    const val ApiKey = "0dad551ec0f84ed02907ff5c42e8ec70"
    const val ApiSecret = "9e8bb54dc3288cdf"
    const val MimeTypeJson = "application/json; charset=utf-8"

    val UserAgent = "api-client/1 com.douban.frodo/${VersionUtils.getVersionName()}(${VersionUtils.getVersionCode()}) Android/23 product/capricorn vendor/Xiaomi model/MI 5s  rom/miui6  network/wifi"
    val CurrentUser = User(uid = "3824320", name = "14号的传奇", largeAvatar = "https://img3.doubanio.com/icon/up3824320-3.jpg")

    object API {
        const val RecommendFeeds = "api/v2/recommend_feed"
        const val MixedRecGroups = "api/v2/group/mixed_rec_groups"
        const val Timeline = "api/v2/status/home_timeline"
        const val RecommendTimeline = "api/v2/status/recommended_timeline"
        const val SubjectMovie = "api/v2/movie/modules"
        const val AuthToken = "service/auth2/token"

        val CurrentUser = "api/v2/user/${Constants.CurrentUser.uid}"
        val JoinedGroups = "api/v2/group/user/${Constants.CurrentUser.uid}/joined_groups"

        fun noteDetail(id: String): String = "api/v2/note/$id"
        fun groupTopicDetail(id: String): String = "api/v2/group/topic/$id"
        fun reviewDetail(id: String): String = "api/v2/review/$id"
        fun photoAlbumDetail(id: String): String = "api/v2/photo_album/$id"

    }

    object Rexxar {
        const val Api = "douban://douban.com"
        const val PartialApi = "douban://partial.douban.com"
        const val RouteApi = "https://frodo.douban.com/frodo_rexxar/api/routes"
        val ProxyHosts = listOf(Host)
    }

    object Broadcast {
        const val LoginExpired = "LoginExpired"
    }

    object Font {
        const val Path = "fonts/fzlth.ttf"
    }

    object ActivityReqId {
        const val Success = 0
    }

}