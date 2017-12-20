package com.thierry.douban.util

/**
 * Created by Thierry on 2017/7/6.
 */

enum class FetchDataResult {
    Normal,
    Success,
    Failed
}

enum class UnauthorizedCode(val code: Int, val message: String) {
    InvalidAccessToken(103, "登录信息过期"),
    UsernamePasswordMismatch(120, "用户名密码不匹配")
}

enum class HttpStatusCode(val code: Int, val message: String) {
    UnAccessible(-1, "网络无连接,请检查网络设置"),
    Success(200, ""),
    Error(500, ""),
    NotFound(404, ""),
    Unauthorized(400, "")
}

enum class HttpMethod {
    Get,
    Post,
    Put,
    Delete
}

enum class CardLayout(val type: Int) {
    HomeGallery(0),
    HomeFeedNormal(1),
    HomeFeedNormalPortrait(2),

    StatusFind(200),
    StatusNormal(201),      //普通状态
    StatusReshares(202),    //转发的状态
    StatusCard(203),        //带卡片的状态

    StatusRecommendTop(214),    //推荐状态-顶部
    StatusRecommendNormal(215), //推荐状态-普通

    GroupTop(300),
    GroupJoined(301),
    GroupRec(302),

    ProfileTop(401),
    ProfileNotification(402),
    ProfileMenu(403),

    SettingsNormal(501),
    SettingsButton(502)
}

enum class FeedTargetType(val type: Int) {
    Note(1015),
    Page(1060),
    Review(1012),
    PhotoAlbum(1026),
    GroupTopic(1013)
}