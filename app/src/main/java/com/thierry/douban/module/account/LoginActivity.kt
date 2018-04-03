package com.thierry.douban.module.account

import android.databinding.Observable
import android.util.Log
import android.view.View
import android.widget.RelativeLayout.LayoutParams
import com.thierry.douban.R
import com.thierry.douban.module.common.BaseActivity
import com.thierry.douban.util.Constants
import com.thierry.douban.util.FetchDataResult
import com.thierry.douban.util.ResUtils
import kotlinx.android.synthetic.main.action_bar_common.*
import kotlinx.android.synthetic.main.activity_login.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import org.jetbrains.anko.toast


/**
 * Created by Thierry on 2017/7/22.
 */
class LoginActivity : BaseActivity(), View.OnFocusChangeListener, View.OnClickListener {

    override val viewModel = LoginViewModel()

    override fun getLayoutId() = R.layout.activity_login

    override fun getActionBarTitle() = "登录豆瓣"

    override fun initView() {
        super.initView()
        actionBarTitle?.visibility = View.GONE
        actionBarBottomLine?.visibility = View.GONE
        setListeners()
        addKVO()
    }

    fun setListeners() {
        usernameText?.onFocusChangeListener = this
        passwordText?.onFocusChangeListener = this
        loginBtn?.setOnClickListener(this)
        KeyboardVisibilityEvent.setEventListener(this) {
            handleKeyboardToggle(it)
        }
    }

    fun handleKeyboardToggle(opened: Boolean) {
        actionBarTitle?.visibility = if (opened) View.VISIBLE else View.GONE
        actionBarBottomLine?.visibility = if (opened) View.VISIBLE else View.GONE
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        var marginTop = ResUtils.getDimen(R.dimen.login_welcome_margin_top).toInt()
        if (opened) {
            marginTop = -marginTop
        }
        layoutParams.setMargins(0, marginTop, 0, 0)
        loginWrapper.layoutParams = layoutParams
    }

    fun addKVO() {
        viewModel.fetchDataResult.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                this@LoginActivity.observeHandler(viewModel.fetchDataResult.get()!!)
            }
        })
    }

    fun observeHandler(newValue: FetchDataResult) {
        if (newValue == FetchDataResult.Success) {
            setResult(Constants.ActivityReqId.Success)
            finish()
        } else if (newValue == FetchDataResult.Failed) {
            Log.d(TAG, viewModel.errorMessage)
            if (!viewModel.errorMessage.isEmpty()) {
                toast(viewModel.errorMessage)
            }
        }
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus) {
            v?.background = ResUtils.getDrawableByName("border_green")
        } else {
            v?.background = ResUtils.getDrawableByName("border_gray")
        }
    }

    override fun onClick(v: View?) {
        toast("正在登录...")
        viewModel.doLogin("${usernameText?.text}", "${passwordText?.text}")
    }
}