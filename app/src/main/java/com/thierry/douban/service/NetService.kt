package com.thierry.douban.service

import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import com.thierry.douban.App
import com.thierry.douban.util.*
import com.thierry.douban.util.HttpStatusCode
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject

/**
 * Created by Thierry on 2017/6/24.
 */
typealias SuccessCallback = (String?) -> Unit

typealias FailedCallback = (String?) -> Unit

class NetService private constructor() {

    private val TAG: String? = NetService::class.java.canonicalName
    private val localBroadcastManager: LocalBroadcastManager? = LocalBroadcastManager.getInstance(App.instance)

    private object Holder {
        val INSTANCE = NetService()
    }

    companion object {
        fun instance(): NetService {
            FuelManager.instance.basePath = Constants.Host
            FuelManager.instance.baseHeaders = Holder.INSTANCE.baseHeader()
            FuelManager.instance.baseParams = Holder.INSTANCE.baseParams()
            return Holder.INSTANCE
        }
    }

    private fun baseHeader(): Map<String, String> {
        val header = mutableMapOf("User-Agent" to Constants.UserAgent)
        if (UserService.instance().fetchUser() != null) {
            header["Authorization"] = "Bearer ${UserService.instance().fetchUser()?.accessToken}"
        }
        return header
    }

    private fun baseParams(): List<Pair<String, String>> {
        return listOf(
                "apikey" to Constants.ApiKey,
                "loc_id" to "108288",
                "device_id" to "79e12749ff28e0675d7939facb6e5ca5407e54fc",
                "os_rom" to "miui6",
                "udid" to "79e12749ff28e0675d7939facb6e5ca5407e54fc",
                "channel" to "Xiaomi_Market"
        )
    }

    fun resetBaseHeader() {
        FuelManager.instance.baseHeaders = baseHeader()
    }

    /**
     * Http Do Get
     *
     * @param url
     * @param params
     * @param success
     * @param failed
     */
    fun doGet(url: String, params: List<Pair<String, Any?>>? = null, success: SuccessCallback? = null, failed: FailedCallback? = null) {
        return doRequest(url, HttpMethod.Get, params, success, failed)
    }

    /**
     * Http Do Post
     *
     * @param url
     * @param params
     * @param success
     * @param failed
     */
    fun doPost(url: String, params: List<Pair<String, Any?>>? = null, success: SuccessCallback? = null, failed: FailedCallback? = null) {
        return doRequest(url, HttpMethod.Post, params, success, failed)
    }

    /**
     * Http Do Request
     *
     * @param url
     * @param method
     * @param params
     * @param success
     * @param failed
     */
    fun doRequest(url: String, method: HttpMethod, params: List<Pair<String, Any?>>?, success: SuccessCallback? = null, failed: FailedCallback? = null) {
        Log.d(TAG, url)
        doAsync {
            when (method) {
                HttpMethod.Get -> {
                    url.httpGet(params).responseString { _, response, result ->
                        uiThread {
                            handleResponse(response, result, success, failed)
                        }
                    }
                }
                HttpMethod.Post ->
                    url.httpPost(params).responseString { _, response, result ->
                        uiThread {
                            handleResponse(response, result, success, failed)
                        }
                    }
                else -> {
                }
            }

        }
    }

    /**
     * 处理请求Response
     */
    fun handleResponse(response: Response, result: Result<String, FuelError>, success: SuccessCallback? = null, failed: FailedCallback? = null) {
        val statusCode = response.httpStatusCode
        var errorData = response.getErrorInfo()
        when (statusCode) {
            HttpStatusCode.Success.code ->
                success?.invoke(result.getAs<String>())
            HttpStatusCode.UnAccessible.code ->
                failed?.invoke(HttpStatusCode.UnAccessible.message)
            HttpStatusCode.Unauthorized.code -> {
                this.handleUnauthorized(errorData.first, failed)
            }
            else -> failed?.invoke(errorData.second)
        }
    }

    /**
     * 处理请求Response 400的情况（登录过期或没有权限）
     */
    fun handleUnauthorized(code: Int, failed: FailedCallback? = null) {
        when (code) {
            UnauthorizedCode.InvalidAccessToken.code -> {
                val intent: Intent = Intent()
                intent.action = Constants.Broadcast.LoginExpired
                localBroadcastManager?.sendBroadcast(intent)
                failed?.invoke(UnauthorizedCode.InvalidAccessToken.message)
            }
            UnauthorizedCode.UsernamePasswordMismatch.code -> {
                failed?.invoke(UnauthorizedCode.UsernamePasswordMismatch.message)
            }
        }
    }

    /**
     * 从Response中获取错误信息
     */
    fun Response.getErrorInfo(): Pair<Int, String> {
        val data = JSONObject(String(data))
        val message: String = try {
            data.getString("localized_message")
        } catch (e: Exception) {
            ""
        }
        val code: Int = try {
            data.getInt("code")
        } catch (e: Exception) {
            0
        }
        return Pair(code, message)
    }
}