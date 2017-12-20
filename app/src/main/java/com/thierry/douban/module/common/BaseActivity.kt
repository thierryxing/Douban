package com.thierry.douban.module.common

import android.app.Activity
import android.app.Fragment
import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thierry.douban.BR
import kotlinx.android.synthetic.main.action_bar_feed.*

/**
 * Created by Thierry on 16/2/19.
 */
abstract class BaseActivity : Activity() {

    val TAG: String? = this.javaClass.canonicalName
    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initView()
    }

    fun initBinding() {
        val mBinding = DataBindingUtil.setContentView<ViewDataBinding>(this, getLayoutId())
        mBinding.setVariable(BR.viewModel, viewModel)
    }

    /**
     * 返回当前Activity的LayoutId
     */
    abstract @LayoutRes fun getLayoutId(): Int

    /**
     * 返回当前ActionBar的Title
     */
    open fun getActionBarTitle(): String = ""

    /**
     * 初始化方法
     */
    open fun initView() {
        actionBarTitle?.text = getActionBarTitle()
        actionBarBackBtn?.setOnClickListener { finish() }
    }

    /**
     * replaceFragment

     * @param layoutId
     * *
     * @param fragment
     * *
     * @param tag
     */
    fun replaceFragmentByTag(@IdRes layoutId: Int, fragment: Fragment, tag: String) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(layoutId, fragment, tag)
        transaction.commitAllowingStateLoss()
        fragmentManager.executePendingTransactions()
    }
}
