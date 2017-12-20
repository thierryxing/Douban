package com.thierry.douban.component

import android.app.Activity
import android.app.Fragment
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thierry.douban.R
import com.thierry.douban.databinding.FragmentRecyclerBinding
import com.thierry.douban.model.Card
import com.thierry.douban.module.common.BaseFragment
import com.thierry.douban.util.FetchDataResult
import kotlinx.android.synthetic.main.fragment_recycler.*
import org.jetbrains.anko.toast

class RecyclerFragment : Fragment() {

    companion object {
        val DISABLE_SWIPE_REFRESH = "disableSwipeRefresh"
        val DISABLE_LOADING_ANIMATION = "disableLoadingAnimation"
    }
    var viewModel: RecyclerViewModel? = null

    private val TAG = CardAdapter::class.java.canonicalName
    private var adapter: CardAdapter? = null
    private var mItemClickCallback: OnItemClickListener? = null
    private var viewCache: View? = null
    private var disableSwipeRefresh = true
    private var disableLoadingAnimation = false
    private var cached: Boolean = false


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (viewCache == null) {
            viewCache = initBinding(inflater, container)
            initParams()
            addKVO()
            fetchData()
        }
        return viewCache!!
    }

    fun initParams() {
        disableSwipeRefresh = arguments.getBoolean(DISABLE_SWIPE_REFRESH)
        disableLoadingAnimation = arguments.getBoolean(DISABLE_LOADING_ANIMATION)
        viewModel?.let { adapter = CardAdapter(it, mItemClickCallback) }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!cached) {
            mRecyclerView?.layoutManager = LinearLayoutManager(activity)
            mRecyclerView?.adapter = adapter
            mSwipeRefresh?.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
            mSwipeRefresh?.isEnabled = !disableSwipeRefresh
            mSwipeRefresh?.setOnRefreshListener({
                fetchData()
            })
            if (!disableLoadingAnimation) {
                showLoading()
            }
            cached = true
        }
    }

    private fun initBinding(inflater: LayoutInflater?, container: ViewGroup?): View {
        val mBinding: FragmentRecyclerBinding = DataBindingUtil.inflate<FragmentRecyclerBinding>(inflater, R.layout.fragment_recycler, container, false)
        val view = mBinding.root
        mBinding.viewModel = viewModel
        return view
    }

    fun fetchData() {
        viewModel?.fetchData()
    }

    private fun addKVO() {
        viewModel?.fetchDataResult?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                viewModel?.let {
                    this@RecyclerFragment.observeHandler(it.fetchDataResult.get())
                }
            }
        })
    }

    fun observeHandler(newValue: FetchDataResult) {
        hideLoading()
        if (newValue == FetchDataResult.Success) {
            mRecyclerView?.adapter?.notifyDataSetChanged()
        } else if (newValue == FetchDataResult.Failed) {
            viewModel?.let {
                if (!it.errorMessage.isEmpty()) {
                    toast(it.errorMessage)
                }
            }
        }
    }

    private fun hideLoading() {
        mSwipeRefresh?.isRefreshing = false
        topProgressBar?.visibility = View.GONE
    }

    private fun showLoading() {
        topProgressBar?.visibility = View.VISIBLE
    }

    fun setItemClickCallBack(fragment: BaseFragment) {
        mItemClickCallback = fragment as OnItemClickListener
    }

    fun setItemClickCallBack(activity: Activity) {
        mItemClickCallback = activity as OnItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(card: Card)
    }
}
