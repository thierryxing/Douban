package com.thierry.douban.module.common

import android.app.Fragment
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.action_bar_first.*

/**
 * Created by Thierry on 16/2/19.
 */
abstract class BaseFragment : Fragment() {

    val TAG: String? = this.javaClass.canonicalName
    var viewCache: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (viewCache == null) {
            viewCache = inflater!!.inflate(getLayoutId(), container, false)
        }
        return viewCache
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBarTitle?.text = getTitle()
        initView(view)
    }

    /**
     * 返回当前Fragment的LayoutId
     */
    abstract @LayoutRes fun getLayoutId(): Int

    /**
     * 返回当前ActionBar的Title
     * 重点知识：https://kotlinlang.org/docs/reference/idioms.html#single-expression-functions
     */
    open fun getTitle(): String = ""

    /**
     * 初始化方法
     */
    open fun initView(view: View?) {}

    /**
     * replaceFragment

     * @param layoutId
     * *
     * @param fragment
     * *
     * @param tag
     */
    fun replaceFragmentByTag(@IdRes layoutId: Int, fragment: Fragment, tag: String) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(layoutId, fragment, tag)
        transaction.commitAllowingStateLoss()
        childFragmentManager.executePendingTransactions()
    }

    /**
     * 设置ActionBar左侧按钮
     *
     * @param bgResId [Int]
     * @param click [(Unit) -> Unit]
     */
    fun addLeftActionBtn(@DrawableRes bgResId: Int, click: ((view: View) -> Unit)? = null) {
        addActionBtn(actionBarLeftBtn, bgResId, click)
    }

    /**
     * 重点知识：参数可以加默认值，调用时可以不写
     * com/thierry/douban/module/profile/ProfileFragment.kt:27
     *
     * 设置ActionBar右侧按钮
     *
     * @param bgResId [Int]
     * @param click [(Unit) -> Unit]
     */
    fun addRightActionBtn(@DrawableRes bgResId: Int, click: ((view: View) -> Unit)? = null) {
        addActionBtn(actionBarRightBtn, bgResId, click)
    }

    /**
     * 重点知识点：简化Listener
     * https://wangjiegulu.gitbooks.io/kotlin-for-android-developers-zh/jian_hua_setonclicklistener.html
     *
     * @param btn [Button]
     * @param bgResId [Int]
     * @param click [(Unit) -> Unit]
     */
    private fun addActionBtn(btn: Button?, @DrawableRes bgResId: Int, click: ((view: View) -> Unit)?) {
        btn?.visibility = View.VISIBLE
        btn?.setBackgroundResource(bgResId)
        if (click != null) {
            btn?.setOnClickListener { click(it) }
        }
    }
}