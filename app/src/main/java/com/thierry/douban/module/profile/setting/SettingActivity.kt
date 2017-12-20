package com.thierry.douban.module.profile.setting

import com.thierry.douban.R
import com.thierry.douban.component.RecyclerFragment
import com.thierry.douban.extension.instanceOf
import com.thierry.douban.model.Card
import com.thierry.douban.model.SettingItem
import com.thierry.douban.module.common.BaseActivity
import com.thierry.douban.service.NetService
import com.thierry.douban.service.UserService
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton


/**
 * Created by Thierry on 2017/7/22.
 */
class SettingActivity : BaseActivity(), RecyclerFragment.OnItemClickListener {
    var recyclerFragment: RecyclerFragment? = null

    override val viewModel = SettingViewModel()

    override fun getLayoutId() = R.layout.activity_setting

    override fun getActionBarTitle() = "设置"

    override fun initView() {
        super.initView()
        addRecyclerFragment()
    }

    fun addRecyclerFragment() {
        recyclerFragment = instanceOf<RecyclerFragment>(RecyclerFragment.DISABLE_SWIPE_REFRESH to true, RecyclerFragment.DISABLE_LOADING_ANIMATION to true)
        recyclerFragment?.viewModel = viewModel
        recyclerFragment?.setItemClickCallBack(this)
        replaceFragmentByTag(R.id.recyclerFragment, recyclerFragment!!, this.TAG.toString())
    }

    /**
     * 重点知识：Anko startActivity<T>
     */
    override fun onItemClick(card: Card) {
        card as SettingItem
        if (card.title == "退出登录") {
            alert("确认退出登录", "豆瓣") {
                yesButton { doLogout() }
                noButton { }
            }.show()
        }
    }

    fun doLogout() {
        UserService.instance().deleteUser()
        NetService.instance().resetBaseHeader()
        finish()
    }
}