package com.thierry.douban.module.home

import android.view.View
import com.thierry.douban.R
import com.thierry.douban.component.RecyclerFragment
import com.thierry.douban.extension.instanceOf
import com.thierry.douban.model.Card
import com.thierry.douban.model.Feed
import com.thierry.douban.module.common.BaseFragment
import com.thierry.douban.module.home.target.FeedTargetActivity
import org.jetbrains.anko.startActivity


class FeedFragment : BaseFragment(), RecyclerFragment.OnItemClickListener {

    override fun getLayoutId(): Int = R.layout.fragment_feed
    val viewModel: FeedViewModel = FeedViewModel()
    var recyclerFragment: RecyclerFragment? = null

    override fun initView(view: View?) {
        super.initView(view)
        addRecyclerFragment()
    }

    fun addRecyclerFragment() {
        if (recyclerFragment == null) {
            recyclerFragment = instanceOf<RecyclerFragment>()
            recyclerFragment?.setItemClickCallBack(this)
            recyclerFragment?.viewModel = viewModel
        }
        replaceFragmentByTag(R.id.recyclerFragment, recyclerFragment!!, this.TAG.toString())
    }

    /**
     * 重点知识：Anko startActivity<T>
     */
    override fun onItemClick(card: Card) {
        val feed = card as? Feed
        feed?.target?.let {
            startActivity<FeedTargetActivity>(
                    Pair("uri", it.uri),
                    Pair("id", it.id),
                    Pair("title", feed.title),
                    Pair("kind", it.kind))
        }
    }

}
