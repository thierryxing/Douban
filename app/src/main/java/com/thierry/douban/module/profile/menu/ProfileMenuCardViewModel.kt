package com.thierry.douban.module.profile.menu

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.thierry.douban.R
import com.thierry.douban.component.CardViewModel
import com.thierry.douban.model.SimpleItem

/**
 * Created by Thierry on 2017/7/15.
 */
class ProfileMenuCardViewModel(val menus: List<SimpleItem>) : CardViewModel() {

    override fun fetchData() {
        buildCardList()
    }

    override fun buildCardList() {
        cardList.addAll(menus)
    }

    override fun getCardLayoutId(viewType: Int): Int
            = R.layout.item_profile_menu

    override fun getCardViewModel(position: Int): ProfileMenuItemViewModel
            = ProfileMenuItemViewModel(menus[position])

    override fun getLayoutManager(context: Context): GridLayoutManager
            = GridLayoutManager(context, 4)

}