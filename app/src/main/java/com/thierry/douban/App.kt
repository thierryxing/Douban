package com.thierry.douban

import android.app.Application
import com.thierry.douban.service.NetService
import com.thierry.douban.util.Constants
import kotlin.properties.Delegates
import android.graphics.Typeface
import android.util.Log
import com.douban.rexxar.Rexxar
import com.douban.rexxar.route.RouteManager
import java.lang.reflect.Field
import okhttp3.OkHttpClient
import android.text.TextUtils
import com.douban.rexxar.resourceproxy.ResourceProxy
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


/**
 * Created by Thierry on 16/2/29.
 */
class App : Application() {

    companion object {
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initRexxar()
        initFont()
        initNet()
    }

    private fun initRexxar() {
        Rexxar.initialize(this)
//        Rexxar.setDebug(true)
        RouteManager.getInstance().setRouteApi(Constants.Rexxar.RouteApi)
        RouteManager.getInstance().refreshRoute(null)
        Rexxar.setHostUserAgent(Constants.UserAgent)
        ResourceProxy.getInstance().addProxyHosts(Constants.Rexxar.ProxyHosts)
//        Rexxar.setOkHttpClient(OkHttpClient().newBuilder()
//                .retryOnConnectionFailure(true)
//                .addNetworkInterceptor(AuthInterceptor())
//                .build())
    }

    private fun initFont() {
        val typeFaceYaHei = Typeface.createFromAsset(assets, Constants.Font.Path)
        val field = Typeface::class.java.getDeclaredField("SERIF")
        field.isAccessible = true
        field.set(null, typeFaceYaHei)
    }

    private fun initNet() {
        NetService.instance()
    }


}
