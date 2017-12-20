package com.thierry.douban.module.status

import android.util.Log
import android.view.View
import com.thierry.douban.extension.instanceOf
import com.thierry.douban.R
import com.thierry.douban.component.RecyclerFragment
import com.thierry.douban.module.common.BaseFragment
import com.thierry.douban.extension.loadRound
import com.thierry.douban.module.profile.StatusViewModel
import com.thierry.douban.util.Constants
import kotlinx.android.synthetic.main.action_bar_first.*
import kotlinx.android.synthetic.main.partial_status_top.*

open class StatusFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_status
    open val viewModel = StatusViewModel()
    var recyclerFragment: RecyclerFragment? = null

    override fun initView(view: View?) {
        super.initView(view)
        addRecyclerFragment()
        actionBarTitle?.text = viewModel.title
        Constants.CurrentUser.largeAvatar?.let { statusCurrentAvatar.loadRound(it) }
        addLeftActionBtn(R.drawable.ic_status_search_user) { leftBtnClick(it) }
        addRightActionBtn(R.drawable.ic_chat_green) { rightBtnClick(it) }
    }

    fun addRecyclerFragment() {
        if (recyclerFragment == null) {
            recyclerFragment = instanceOf<RecyclerFragment>()
            recyclerFragment?.viewModel = viewModel
        }
        replaceFragmentByTag(R.id.recyclerFragment, recyclerFragment!!, this.TAG.toString())
    }

    fun leftBtnClick(view: View) {
        Log.d(TAG, "leftBtnClick: $view")
    }

    fun rightBtnClick(view: View) {
        Log.d(TAG, "leftBtnClick: $view")
    }


}
