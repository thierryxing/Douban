package com.thierry.douban.module.home.target


import android.databinding.Observable
import com.thierry.douban.R
import com.thierry.douban.module.common.BaseActivity
import com.thierry.douban.extension.instanceOf
import com.thierry.douban.component.RexxarPartialFragment
import com.thierry.douban.util.FetchDataResult
import org.jetbrains.anko.toast


class FeedTargetActivity : BaseActivity() {

    private var uri: String = ""
    override val viewModel = FeedTargetViewModel()

    override fun getLayoutId() = R.layout.activity_feed_target

    override fun initView() {
        super.initView()
        getParams()
        initRexxar()
        addKVO()
        viewModel.fetchNote()
    }

    fun initRexxar() {
        val fragment = instanceOf<RexxarPartialFragment>("uri" to uri)
        replaceFragmentByTag(R.id.mRexxarWebView, fragment, "note")
    }

    fun getParams() {
        uri = intent.getStringExtra("uri")
        viewModel.kind = intent.getIntExtra("kind", 0)
        viewModel.id = intent.getStringExtra("id")
        viewModel.title = intent.getStringExtra("title")
    }

    fun addKVO() {
        viewModel.fetchDataResult.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                this@FeedTargetActivity.observeHandler(viewModel.fetchDataResult.get())
            }
        })
    }

    fun observeHandler(newValue: FetchDataResult) {
        if (newValue == FetchDataResult.Success) {
            viewModel.notifyChange()
        } else if (newValue == FetchDataResult.Failed) {
            toast(viewModel.errorMessage)
        }
    }

}
