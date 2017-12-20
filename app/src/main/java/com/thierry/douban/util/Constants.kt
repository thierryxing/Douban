package com.thierry.douban.util

import com.thierry.douban.model.User

/**
 * Created by Thierry on 2017/6/25.
 */

object Constants {

    val Host = "https://frodo.douban.com"
    val ApiKey = "0dad551ec0f84ed02907ff5c42e8ec70"
    val ApiSecret = "9e8bb54dc3288cdf"

    val UserAgent = "api-client/1 com.douban.frodo/${VersionUtils.getVersionName()}(${VersionUtils.getVersionCode()}) Android/23 product/capricorn vendor/Xiaomi model/MI 5s  rom/miui6  network/wifi"
    val MimeTypeJson = "application/json; charset=utf-8"

    val CurrentUser = User(uid = "3824320", name = "14号的传奇", largeAvatar = "https://img3.doubanio.com/icon/up3824320-3.jpg")

    object API {
        val RecommendFeeds = "api/v2/recommend_feed"
        val JoinedGroups = "api/v2/group/user/${Constants.CurrentUser.uid}/joined_groups"
        val MixedRecGroups = "api/v2/group/mixed_rec_groups"
        val CurrentUser = "api/v2/user/${Constants.CurrentUser.uid}"
        val Timeline = "api/v2/status/home_timeline"
        val RecommendTimeline = "api/v2/status/recommended_timeline"
        val SubjectMovie = "api/v2/movie/modules"

        val AuthToken = "service/auth2/token"

        fun noteDetail(id: String): String = "api/v2/note/$id"
        fun groupTopicDetail(id: String): String = "api/v2/group/topic/$id"
        fun reviewDetail(id: String): String = "api/v2/review/$id"
        fun photoAlbumDetail(id: String): String = "api/v2/photo_album/$id"

    }

    object Rexxar {
        val Api = "douban://douban.com"
        val PartialApi = "douban://partial.douban.com"
        val RouteApi = "https://raw.githubusercontent.com/thierryxing/DBTest/master/app/src/main/assets/rexxar/routes.json"
        val ProxyHosts = listOf("raw.githubusercontent.com")
    }

    object Broadcast {
        val LoginExpired = "LoginExpired"
    }

    object Font {
        val Path = "fonts/fzlth.ttf"
    }

    object ActivityReqId {
        val Success = 0
    }

}