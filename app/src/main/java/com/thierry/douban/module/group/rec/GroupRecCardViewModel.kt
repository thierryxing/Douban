package com.thierry.douban.module.group.rec

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.thierry.douban.R
import com.thierry.douban.component.CardViewModel
import com.thierry.douban.model.Promo

/**
 * Created by Thierry on 2017/7/14.
 */
class GroupRecCardViewModel(val list: List<Promo>) : CardViewModel() {

    override fun fetchData() {
        buildCardList()
    }

    override fun buildCardList() {
        cardList.addAll(list)
    }

    override fun getCardLayoutId(viewType: Int): Int = R.layout.item_group_rec

    override fun getCardViewModel(position: Int): GroupRecItemViewModel {
        return GroupRecItemViewModel(list[position])
    }

}