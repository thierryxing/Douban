package com.thierry.douban.module.group.joined

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.thierry.douban.R
import com.thierry.douban.component.CardViewModel
import com.thierry.douban.model.Group

/**
 * Created by Thierry on 2017/7/14.
 */
class GroupJoinedCardViewModel(var list: List<Group>) : CardViewModel() {

    override fun fetchData() {
        buildCardList()
    }

    override fun buildCardList() {
        cardList.addAll(list)
    }

    override fun getCardViewModel(position: Int): GroupJoinedItemViewModel {
        return GroupJoinedItemViewModel(list[position])
    }

    override fun getCardLayoutId(viewType: Int): Int = R.layout.item_group_joined

    override fun getLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}