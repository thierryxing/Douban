package com.thierry.douban.module.group

import android.view.View
import com.thierry.douban.R
import com.thierry.douban.component.RecyclerFragment
import com.thierry.douban.extension.instanceOf
import com.thierry.douban.module.common.BaseFragment
import kotlinx.android.synthetic.main.action_bar_first.*

class GroupFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_group
    val viewModel: GroupViewModel = GroupViewModel()
    var recyclerFragment: RecyclerFragment? = null

    override fun initView(view: View?) {
        super.initView(view)
        actionBarTitle?.text = viewModel.title
        addRecyclerFragment()
        addLeftActionBtn(R.drawable.ic_actionbar_search_icon)
        addRightActionBtn(R.drawable.ic_chat_green)
    }

    fun addRecyclerFragment() {
        if (recyclerFragment == null) {
            recyclerFragment = instanceOf<RecyclerFragment>()
            recyclerFragment?.viewModel = viewModel
        }
        replaceFragmentByTag(R.id.recyclerFragment, recyclerFragment!!, this.TAG.toString())
    }
}
