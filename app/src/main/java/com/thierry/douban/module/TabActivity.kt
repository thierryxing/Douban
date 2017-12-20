package com.thierry.douban.module

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.design.widget.TabLayout
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.view.View
import com.thierry.douban.module.common.BaseActivity
import com.thierry.douban.R
import com.thierry.douban.module.account.LoginActivity
import com.thierry.douban.module.common.BaseFragment
import com.thierry.douban.module.group.GroupFragment
import com.thierry.douban.module.home.FeedFragment
import com.thierry.douban.module.profile.ProfileFragment
import com.thierry.douban.module.status.StatusFragment
import com.thierry.douban.module.status.recommend.RecommendStatusFragment
import com.thierry.douban.module.subject.SubjectFragment
import com.thierry.douban.service.UserService
import com.thierry.douban.util.Constants
import com.thierry.douban.util.ResUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_tab.view.*
import org.jetbrains.anko.collections.forEachWithIndex

class TabActivity : BaseActivity(), TabLayout.OnTabSelectedListener {

    private val mFragmentList: MutableList<BaseFragment> = mutableListOf(
            FeedFragment(), SubjectFragment(), RecommendStatusFragment(), GroupFragment(), ProfileFragment()
    )
    private var mBroadcastReceiver: AuthBroadcastReceiver? = null
    private var localBroadcastManager: LocalBroadcastManager? = null
    private var currentTabIndex = 0

    override val viewModel: TabViewModel
        get() = TabViewModel()

    override fun getLayoutId() = R.layout.activity_main

    override fun initView() {
        super.initView()
        registerBroadcast()
        initTabBar()
    }

    /**
     * 重点知识点：
     * (1)forEach，forEachWithIndex
     */
    private fun initTabBar() {
        initFragments()
        mTabLayout.removeAllTabs()
        viewModel.tabItems.forEachWithIndex { index, _ ->
            val tabView = layoutInflater.inflate(R.layout.item_tab, null)
            tabView.initTabContent(index, index == 0)
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(tabView), index == 0)
        }
        mTabLayout.addOnTabSelectedListener(this)
        selectFragment(0)
    }

    private fun initFragments() {
        if (UserService.instance().isLoggedIn()) {
            mFragmentList[2] = StatusFragment()
        }
    }

    private fun selectFragment(position: Int) {
        currentTabIndex = position
        val fragmentTag = viewModel.tabItems[position].name
        this.replaceFragmentByTag(R.id.contentFrame, mFragmentList[position], fragmentTag)
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        tab.customView?.initTabContent(tab.position, true)
        selectFragment(tab.position)
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {
        tab.customView?.initTabContent(tab.position, false)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    /**
     * 重点知识点：
     * （1）为View扩展一个方法，便于调用
     * （2）if else 表达式
     * （3）Kotlin Android Extensions inflating from other view 需要增加view.
     *
     * @param position [Int]
     * @param selected [Boolean]
     */
    fun View.initTabContent(position: Int, selected: Boolean) {
        val tabItem = viewModel.tabItems[position]
        tabText.text = tabItem.title
        tabText.setTextColor(ResUtils.getColor(if (selected) R.color.colorPrimary else R.color.colorGray))
        tabIcon.setImageDrawable(ResUtils.getDrawableByName("ic_tab_${tabItem.name}_${if (selected) "active" else "normal"}"))
    }

    private fun registerBroadcast() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this)
        mBroadcastReceiver = AuthBroadcastReceiver(this)
        val intentFilter = IntentFilter()
        intentFilter.addAction(Constants.Broadcast.LoginExpired)
        localBroadcastManager?.registerReceiver(mBroadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadcastManager?.unregisterReceiver(mBroadcastReceiver)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Constants.ActivityReqId.Success) {
            initTabBar()
        }
    }

    class AuthBroadcastReceiver(val activity: Activity) : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent?.action == Constants.Broadcast.LoginExpired) {
                val mIntent = Intent(context, LoginActivity::class.java)
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                activity.startActivityForResult(mIntent, Constants.ActivityReqId.Success)
            }
        }
    }
}
