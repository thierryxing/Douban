package com.thierry.douban.module.profile

import android.view.View
import com.thierry.douban.R
import com.thierry.douban.component.RecyclerFragment
import com.thierry.douban.extension.instanceOf
import com.thierry.douban.module.common.BaseFragment
import com.thierry.douban.module.profile.setting.SettingActivity
import kotlinx.android.synthetic.main.action_bar_first.*
import org.jetbrains.anko.startActivity

class ProfileFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_group
    val viewModel: ProfileViewModel = ProfileViewModel()
    var recyclerFragment: RecyclerFragment? = null

    override fun initView(view: View?) {
        super.initView(view)
        actionBarTitle?.text = viewModel.title
        addRecyclerFragment()
        addRightActionBtn(R.drawable.ic_settings) {
            settingBtnClick()
        }
    }

    fun addRecyclerFragment() {
        if (recyclerFragment == null) {
            recyclerFragment = instanceOf<RecyclerFragment>(RecyclerFragment.DISABLE_SWIPE_REFRESH to true)
            recyclerFragment?.viewModel = viewModel
        }
        replaceFragmentByTag(R.id.recyclerFragment, recyclerFragment!!, this.TAG.toString())
    }

    fun settingBtnClick() {
        startActivity<SettingActivity>()
    }
}
