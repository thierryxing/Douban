package com.thierry.douban.component


import android.net.Uri
import android.util.Log
import android.view.View
import com.douban.rexxar.resourceproxy.network.RexxarContainerAPI
import com.douban.rexxar.view.RexxarWebChromeClient
import com.thierry.douban.R
import com.thierry.douban.module.common.BaseFragment
import com.thierry.douban.util.Constants
import kotlinx.android.synthetic.main.fragment_rexxar.*
import okhttp3.*

class RexxarPartialFragment : BaseFragment() {

    private var uri: String = ""

    override fun getLayoutId(): Int = R.layout.fragment_rexxar

    override fun initView(view: View?) {
        super.initView(view)
        uri = arguments.getString("uri")
        initWebView()
        loadData()
    }

    fun initWebView() {
        mWebView.setWebChromeClient(RexxarWebChromeClient())
        mWebView.addContainerApi(LogAPI())
        if (uri.startsWith(Constants.Rexxar.Api)) {
            uri = uri.replace(Constants.Rexxar.Api, Constants.Rexxar.PartialApi)
            uri = "$uri/_content"
        }
    }

    fun loadData() {
        if (uri.startsWith(Constants.Rexxar.PartialApi))
            mWebView.loadPartialUri(uri, null)
        else if (uri.startsWith("https://")) {
            mWebView.loadUrl(uri)
        }
    }

    companion object {
        fun newResponseBuilder(request: Request): Response.Builder {
            val responseBuilder = Response.Builder()
            responseBuilder.request(request)
            responseBuilder.code(200)
            responseBuilder.protocol(Protocol.HTTP_1_1)
            return responseBuilder
        }
    }

    class LogAPI : RexxarContainerAPI {

        override fun getPath(): String = "/log"

        override fun call(request: Request?): Response {
            val responseBuilder = newResponseBuilder(request!!)
            val url = request.url().toString()
            val uri = Uri.parse(url)
            val event = uri.getQueryParameter("event")
            val label = uri.getQueryParameter("label")
            Log.d("Rexxar", "event: $event ; label : $label")
            responseBuilder.body(ResponseBody.create(MediaType.parse(Constants.MimeTypeJson), event))
            return responseBuilder.build()
        }
    }
}