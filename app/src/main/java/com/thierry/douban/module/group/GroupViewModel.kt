package com.thierry.douban.module.group

import com.github.salomonbrys.kotson.fromJson
import com.thierry.douban.R
import com.thierry.douban.component.RecyclerViewModel
import com.thierry.douban.model.*
import com.thierry.douban.module.common.BaseViewModel
import com.thierry.douban.module.group.joined.GroupJoinedCardViewModel
import com.thierry.douban.module.group.rec.GroupRecCardViewModel
import com.thierry.douban.module.group.top.GroupTopCardViewModel
import com.thierry.douban.service.NetService
import com.thierry.douban.util.CardLayout
import com.thierry.douban.util.Constants
import com.thierry.douban.util.FetchDataResult

/**
 * Created by Thierry on 2017/7/13.
 */
class GroupViewModel : RecyclerViewModel() {

    var groupList: List<Group>? = null
    var recList: List<Promo>? = null
    var topList: List<SimpleItem> = listOf(
            SimpleItem("最新话题", "latest"),
            SimpleItem("发表的", "posted"),
            SimpleItem("回应的", "replied"),
            SimpleItem("最近浏览", "viewed")
    )
    var title = "小组"

    override var api: String = Constants.API.JoinedGroups

    override fun fetchData() {
        fetchDataResult.set(FetchDataResult.Normal)
        NetService.instance().doGet(api, success = { json: String? ->
            clearData()
            handelJSON(json)
            fetchRecs()
        }, failed = { error ->
            error?.let {
                errorMessage = it
            }
            fetchDataResult.set(FetchDataResult.Failed)
        })
    }

    override fun handelJSON(json: String?) {
        val wrapper = json?.let { gson().fromJson<GroupWrapper>(it) }
        groupList = wrapper?.groups
    }

    fun fetchRecs() {
        fetchDataResult.set(FetchDataResult.Normal)
        NetService.instance().doGet(Constants.API.MixedRecGroups, success = {
            val wrapper = it?.let { gson().fromJson<MixRecGroupWrapper>(it) }
            recList = wrapper?.mixedRecGroups
            buildCardList()
            fetchDataResult.set(FetchDataResult.Success)
        }, failed = {
            it?.let {
                errorMessage = it
            }
            fetchDataResult.set(FetchDataResult.Failed)
        })
    }

    override fun buildCardList() {
        cardList.add(Card(CardLayout.GroupTop.type, topList))
        recList?.let { cardList.add(Card(CardLayout.GroupRec.type, it)) }
        groupList?.let { cardList.add(Card(CardLayout.GroupJoined.type, it)) }
    }

    override fun getCardViewModel(position: Int): BaseViewModel {
        var viewModel: BaseViewModel? = null
        when (cardList[position].layout) {
            CardLayout.GroupTop.type ->
                viewModel = GroupTopCardViewModel(topList)
            CardLayout.GroupRec.type ->
                viewModel = GroupRecCardViewModel(recList ?: listOf())
            CardLayout.GroupJoined.type ->
                viewModel = GroupJoinedCardViewModel(groupList ?: listOf())
        }
        return viewModel!!
    }

    override fun getCardLayoutId(viewType: Int): Int {
        var layoutId: Int = R.layout.card_feed_normal
        when (viewType) {
            CardLayout.GroupTop.type ->
                layoutId = R.layout.card_group_top
            CardLayout.GroupRec.type ->
                layoutId = R.layout.card_group_rec
            CardLayout.GroupJoined.type ->
                layoutId = R.layout.card_group_joined
        }
        return layoutId
    }

}