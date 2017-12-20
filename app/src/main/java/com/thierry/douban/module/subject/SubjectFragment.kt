package com.thierry.douban.module.subject

import android.app.Fragment
import android.support.design.widget.TabLayout
import android.view.View
import com.thierry.douban.R
import com.thierry.douban.component.RecyclerFragment
import com.thierry.douban.extension.instanceOf
import com.thierry.douban.module.common.BaseFragment
import com.thierry.douban.model.SimpleItem
import com.thierry.douban.module.profile.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_subject.*
import org.jetbrains.anko.collections.forEachWithIndex

class SubjectFragment : BaseFragment(), TabLayout.OnTabSelectedListener {

    val TabItems = arrayListOf(
            SimpleItem("电影", "Movie"),
            SimpleItem("读书", "Reading"),
            SimpleItem("电视", "TV"),
            SimpleItem("同城", "Local"),
            SimpleItem("音乐", "Music"),
            SimpleItem("市集", "Market")
    )
    var tabBarInited: Boolean = false
    val mFragmentList: MutableList<Fragment> = mutableListOf()

    override fun getLayoutId(): Int = R.layout.fragment_subject
    override fun getTitle(): String = "书影音"

    override fun initView(view: View?) {
        super.initView(view)
        addLeftActionBtn(R.drawable.ic_actionbar_search_icon)
        addRightActionBtn(R.drawable.ic_chat_green)
        if (!tabBarInited) {
            initTabBar()
            tabBarInited = true
        }
    }

    fun initTabBar() {
        TabItems.forEachWithIndex { index, tabItem ->
            val fragment = instanceOf<RecyclerFragment>()
            fragment.viewModel = SubjectViewModel()
            mFragmentList.add(fragment)
            mSubjectTabLayout.addTab(mSubjectTabLayout.newTab().setText(tabItem.title), index == 0)
        }
        mSubjectTabLayout.addOnTabSelectedListener(this)
        selectFragment(0)
    }

    fun selectFragment(position: Int) {
        val fragmentTag = TabItems[position].name
        this.replaceFragmentByTag(R.id.subjectContentFrame, mFragmentList[position], fragmentTag)
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        selectFragment(tab.position)
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}

}
