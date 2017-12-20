package com.thierry.douban.component

import com.thierry.douban.model.Card
import com.thierry.douban.module.common.BaseViewModel
import com.thierry.douban.module.common.FetchDataViewModel
import com.thierry.douban.service.NetService
import com.thierry.douban.util.FetchDataResult

/**
 * Created by Thierry on 2017/7/14.
 */
abstract class RecyclerViewModel : FetchDataViewModel() {

    var cardList: MutableList<Card> = arrayListOf()

    fun clearData() {
        cardList.clear()
    }

    /**
     * 获取数据
     */
    open fun fetchData() {
        // 如果是本地数据，直接构造CardList
        if (isLocalData) {
            buildCardList()
            return
        }
        fetchDataResult.set(FetchDataResult.Normal)
        NetService.instance().doGet(api, success = { json: String? ->
            clearData()
            handelJSON(json)
            buildCardList()
            fetchDataResult.set(FetchDataResult.Success)
        }, failed = { error ->
            error?.let {
                errorMessage = it
            }
            fetchDataResult.set(FetchDataResult.Failed)
        })
    }

    /**
     * 解析JSON数据并映射到本地对象
     */
    abstract fun handelJSON(json: String?)

    /**
     * 构造CardList
     */
    abstract fun buildCardList()

    /**
     * 返回Card单项的ViewModel
     */
    abstract fun getCardViewModel(position: Int): BaseViewModel

    /**
     * 返回Card单项的ViewModel
     */
    abstract fun getCardLayoutId(viewType: Int): Int

}