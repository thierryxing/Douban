package com.thierry.douban.module.profile

import com.github.salomonbrys.kotson.fromJson
import com.thierry.douban.R
import com.thierry.douban.component.RecyclerViewModel
import com.thierry.douban.model.*
import com.thierry.douban.module.common.BaseViewModel
import com.thierry.douban.module.status.home.card.StatusCardCardViewModel
import com.thierry.douban.module.status.home.card.StatusCardFindViewModel
import com.thierry.douban.module.status.home.card.StatusCardNormalViewModel
import com.thierry.douban.util.CardLayout
import com.thierry.douban.util.Constants

/**
 * Created by Thierry on 2017/7/13.
 */
open class StatusViewModel : RecyclerViewModel() {

    private var timelineList: List<Timeline>? = null
    open val title = "广播"
    override var api: String = Constants.API.Timeline

    override fun handelJSON(json: String?) {
        val wrapper = json?.let { gson().fromJson<TimelineWrapper>(it) }
        timelineList = wrapper?.items?.filter { it.type == "status" && it.status.card != null }
        setLayout()
    }

    /**
     * 暂时先处理普通类型和带卡片类型的Status
     */
    private fun setLayout() {
        timelineList?.map {
            if (it.status.resharesStatus != null) {
                it.layout = CardLayout.StatusReshares.type
            } else if (it.status.card != null) {
                it.layout = CardLayout.StatusCard.type
            } else {
                it.layout = CardLayout.StatusNormal.type
            }
        }
    }

    override fun buildCardList() {
        cardList.add(Card(CardLayout.StatusFind.type))
        timelineList?.let { cardList.addAll(it) }
    }

    override fun getCardViewModel(position: Int): BaseViewModel {
        var viewModel: BaseViewModel? = null
        when (cardList[position].layout) {
            CardLayout.StatusFind.type ->
                viewModel = StatusCardFindViewModel()
            CardLayout.StatusNormal.type ->
                viewModel = StatusCardNormalViewModel(cardList[position] as Timeline)
            CardLayout.StatusCard.type ->
                viewModel = StatusCardCardViewModel(cardList[position] as Timeline)
        }
        return viewModel!!
    }

    override fun getCardLayoutId(viewType: Int): Int {
        var layoutId: Int = R.layout.card_status_find
        when (viewType) {
            CardLayout.StatusFind.type ->
                layoutId = R.layout.card_status_find
            CardLayout.StatusNormal.type ->
                layoutId = R.layout.card_status_normal
            CardLayout.StatusCard.type ->
                layoutId = R.layout.card_status_card
        }
        return layoutId
    }

}