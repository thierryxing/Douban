package com.thierry.douban.module.profile

import com.thierry.douban.R
import com.thierry.douban.component.RecyclerViewModel
import com.thierry.douban.module.common.BaseViewModel
import com.thierry.douban.util.Constants

/**
 * Created by Thierry on 2017/7/13.
 */
class SubjectViewModel : RecyclerViewModel() {

    override var api: String = Constants.API.SubjectMovie

    override fun handelJSON(json: String?) {
//        val wrapper = json?.let { gson().fromJson<TimelineWrapper>(it) }
//        timelineList = wrapper?.items?.filter { it.type == "status" && it.status?.card != null }
    }

    override fun buildCardList() {
//        cardList.add(Card(CardLayout.StatusFind.type))
//        timelineList?.let { cardList.addAll(it) }
    }

    override fun getCardViewModel(position: Int): BaseViewModel {
        return SubjectViewModel()
    }

    override fun getCardLayoutId(viewType: Int): Int {
        var layoutId: Int = R.layout.card_status_find
        return layoutId
    }

}