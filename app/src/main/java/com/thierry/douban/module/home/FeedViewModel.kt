package com.thierry.douban.module.home

import com.github.salomonbrys.kotson.fromJson
import com.thierry.douban.R
import com.thierry.douban.model.Feed
import com.thierry.douban.model.FeedWrapper
import com.thierry.douban.component.RecyclerViewModel
import com.thierry.douban.service.NetService
import com.thierry.douban.util.Constants

/**
 * Created by Thierry on 2017/7/13.
 */
class FeedViewModel : RecyclerViewModel() {

    private var feedList: List<Feed>? = null
    override var api: String = Constants.API.RecommendFeeds

    override fun handelJSON(json: String?) {
        val feedWrapper = json?.let { gson().fromJson<FeedWrapper>(it) }
        feedList = feedWrapper?.recommendFeeds?.filter { arrayListOf(1, 2).contains(it.layout) }
    }

    override fun buildCardList() {
        feedList?.let { cardList.addAll(it) }
    }

    override fun getCardViewModel(position: Int): FeedCardViewModel {
        return FeedCardViewModel(cardList[position] as Feed)
    }

    override fun getCardLayoutId(viewType: Int): Int {
        return R.layout.card_feed_normal
    }

}