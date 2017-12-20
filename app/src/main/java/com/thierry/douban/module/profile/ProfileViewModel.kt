package com.thierry.douban.module.profile

import com.github.salomonbrys.kotson.fromJson
import com.thierry.douban.R
import com.thierry.douban.component.RecyclerViewModel
import com.thierry.douban.model.*
import com.thierry.douban.module.common.BaseViewModel
import com.thierry.douban.module.profile.menu.ProfileMenuCardViewModel
import com.thierry.douban.module.profile.noti.ProfileNotiCardViewModel
import com.thierry.douban.module.profile.top.ProfileTopCardViewModel
import com.thierry.douban.util.CardLayout
import com.thierry.douban.util.Constants

/**
 * Created by Thierry on 2017/7/13.
 */
class ProfileViewModel : RecyclerViewModel() {

    var user: User? = null
    var menus: List<SimpleItem> = arrayListOf(
            SimpleItem("喜欢", "likes"), SimpleItem("日记", "note"), SimpleItem("相册", "album"), SimpleItem("我的广播", "status"),
            SimpleItem("电影·电视", "movies_tvs"), SimpleItem("读书", "books"), SimpleItem("音乐", "music"), SimpleItem("同城活动", "events"),
            SimpleItem("豆瓣时间", "douban_time"), SimpleItem("豆列", "doulist"), SimpleItem("订单", "orders"), SimpleItem("钱包", "wallet"),
            SimpleItem("购物车", "cart")
    )
    var title = "我的"

    override var api: String = Constants.API.CurrentUser

    override fun handelJSON(json: String?) {
        user = json?.let { gson().fromJson<User>(it) }
    }

    override fun buildCardList() {
        cardList.add(Card(CardLayout.ProfileTop.type, user))
        cardList.add(Card(CardLayout.ProfileNotification.type))
        cardList.add(Card(CardLayout.ProfileMenu.type, menus))
    }

    override fun getCardViewModel(position: Int): BaseViewModel {
        var viewModel: BaseViewModel? = null
        when (cardList[position].layout) {
            CardLayout.ProfileTop.type ->
                viewModel = ProfileTopCardViewModel(user)
            CardLayout.ProfileNotification.type ->
                viewModel = ProfileNotiCardViewModel()
            CardLayout.ProfileMenu.type ->
                viewModel = ProfileMenuCardViewModel(menus)
        }
        return viewModel!!
    }

    override fun getCardLayoutId(viewType: Int): Int {
        var layoutId: Int = R.layout.card_feed_normal
        when (viewType) {
            CardLayout.ProfileTop.type ->
                layoutId = R.layout.card_profile_top
            CardLayout.ProfileNotification.type ->
                layoutId = R.layout.card_profile_notification
            CardLayout.ProfileMenu.type ->
                layoutId = R.layout.card_profile_menu
        }
        return layoutId
    }

}