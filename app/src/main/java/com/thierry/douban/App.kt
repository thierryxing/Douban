package com.thierry.douban

import android.app.Application
import com.thierry.douban.service.NetService
import com.thierry.douban.util.Constants
import kotlin.properties.Delegates
import android.graphics.Typeface
import com.douban.rexxar.Rexxar
import com.douban.rexxar.route.RouteManager
import okhttp3.OkHttpClient
import com.douban.rexxar.resourceproxy.ResourceProxy
import android.content.pm.PackageManager
import java.util.*


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
        Rexxar.initialize(
                this,
                true,
                Constants.UserAgent,
                OkHttpClient().newBuilder().retryOnConnectionFailure(true).build(),
                RouteManager.RouteConfig(Constants.Rexxar.RouteApi, getRouteCacheFileName())
        )
        Rexxar.setDebug(BuildConfig.DEBUG)
        RouteManager.getInstance().refreshRoute(null)
        ResourceProxy.getInstance().addProxyHosts(Constants.Rexxar.ProxyHosts)
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

    private fun getRouteCacheFileName(): String {
        try {
            val info = packageManager.getPackageInfo(packageName, 0)
            if (null != info) {
                return String.format(Locale.getDefault(), "routes_%s.json", info.versionName)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return "routes.json"
    }


}
