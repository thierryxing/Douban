package com.thierry.douban.module.status.recommend

import com.github.salomonbrys.kotson.fromJson
import com.thierry.douban.R
import com.thierry.douban.component.RecyclerViewModel
import com.thierry.douban.model.*
import com.thierry.douban.module.common.BaseViewModel
import com.thierry.douban.module.status.recommend.card.StatusCardRecTopViewModel
import com.thierry.douban.module.status.recommend.card.StatusCardRecViewModel
import com.thierry.douban.util.CardLayout
import com.thierry.douban.util.Constants

/**
 * Created by Thierry on 2017/7/13.
 */
class RecommendStatusViewModel : RecyclerViewModel() {

    val title = "推荐广播"
    private var statusList: List<Status>? = null
    override var api: String = Constants.API.RecommendTimeline

    override fun handelJSON(json: String?) {
        val wrapper = json?.let { gson().fromJson<TimelineRecWrapper>(it) }
        statusList = wrapper?.items
        setLayout()
    }

    /**
     * 设置Status的Layout类型
     */
    private fun setLayout() {
        statusList?.map {
            it.layout = CardLayout.StatusRecommendNormal.type
        }
    }

    override fun buildCardList() {
        cardList.add(Card(CardLayout.StatusRecommendTop.type))
        statusList?.let { cardList.addAll(it) }
    }

    override fun getCardViewModel(position: Int): BaseViewModel {
        var viewModel: BaseViewModel? = null
        when (cardList[position].layout) {
            CardLayout.StatusRecommendTop.type ->
                viewModel = StatusCardRecTopViewModel()
            CardLayout.StatusRecommendNormal.type ->
                viewModel = StatusCardRecViewModel(cardList[position] as Status)
        }
        return viewModel!!
    }

    override fun getCardLayoutId(viewType: Int): Int {
        var layoutId: Int = R.layout.card_status_rec_top
        when (viewType) {
            CardLayout.StatusRecommendTop.type ->
                layoutId = R.layout.card_status_rec_top
            CardLayout.StatusRecommendNormal.type ->
                layoutId = R.layout.card_status_rec_normal
        }
        return layoutId
    }

}