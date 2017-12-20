package com.thierry.douban.module.status.recommend

import android.util.Log
import android.view.View
import com.thierry.douban.R
import com.thierry.douban.component.RecyclerFragment
import com.thierry.douban.extension.instanceOf
import com.thierry.douban.module.common.BaseFragment
import kotlinx.android.synthetic.main.action_bar_first.*

/**
 * Created by Thierry on 2017/7/29.
 */
class RecommendStatusFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_status_rec
    val viewModel = RecommendStatusViewModel()
    var recyclerFragment: RecyclerFragment? = null

    override fun initView(view: View?) {
        super.initView(view)
        addRecyclerFragment()
        actionBarTitle?.text = viewModel.title
        addLeftActionBtn(R.drawable.ic_status_search_user) { leftBtnClick(it) }
        addRightActionBtn(R.drawable.ic_chat_green) { rightBtnClick(it) }
    }

    private fun addRecyclerFragment() {
        if (recyclerFragment == null) {
            recyclerFragment = instanceOf<RecyclerFragment>()
            recyclerFragment?.viewModel = viewModel
        }
        replaceFragmentByTag(R.id.recyclerFragment, recyclerFragment!!, this.TAG.toString())
    }

    private fun leftBtnClick(view: View) {
        Log.d(TAG, "leftBtnClick: $view")
    }

    private fun rightBtnClick(view: View) {
        Log.d(TAG, "leftBtnClick: $view")
    }

}