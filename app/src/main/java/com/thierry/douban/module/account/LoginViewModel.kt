package com.thierry.douban.module.account

import com.thierry.douban.model.LoginUser
import com.thierry.douban.module.common.FetchDataViewModel
import com.thierry.douban.service.NetService
import com.thierry.douban.service.UserService
import com.thierry.douban.util.Constants
import com.thierry.douban.util.FetchDataResult

/**
 * Created by Thierry on 2017/7/13.
 */
class LoginViewModel : FetchDataViewModel() {

    var loginUser: LoginUser? = null

    fun resetStatus() {
        errorMessage = ""
        fetchDataResult.set(FetchDataResult.Normal)
    }

    fun doLogin(username: String, password: String) {
        resetStatus()
        if (!isValidate(username, password)) {
            fetchDataResult.set(FetchDataResult.Failed)
            return
        }
        val params = listOf(
                Pair("client_id", Constants.ApiKey),
                Pair("client_secret", Constants.ApiSecret),
                Pair("redirect_uri", "frodo://app/oauth/callback/"),
                Pair("disable_account_create", false),
                Pair("grant_type", "password"),
                Pair("username", username),
                Pair("password", password)
        )
        NetService.instance().doPost(Constants.API.AuthToken, params, { json: String? ->
            UserService.instance().saveUser(json)
            NetService.instance().resetBaseHeader()
            fetchDataResult.set(FetchDataResult.Success)
        }, { error ->
            error?.let {
                errorMessage = it
            }
            fetchDataResult.set(FetchDataResult.Failed)
        })
    }

    fun isValidate(username: String, password: String): Boolean {
        if (username.isEmpty()) {
            errorMessage = "请填写邮箱或电话"
            return false
        }
        if (password.isEmpty()) {
            errorMessage = "请填写密码"
            return false
        }
        return true
    }

}