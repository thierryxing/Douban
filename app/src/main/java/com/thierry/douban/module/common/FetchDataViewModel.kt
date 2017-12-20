package com.thierry.douban.module.common

import android.databinding.ObservableField
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thierry.douban.util.FetchDataResult

/**
 * Created by Thierry on 16/2/19.
 */
abstract class FetchDataViewModel : BaseViewModel() {
    var TAG = this.javaClass.canonicalName
    var fetchDataResult: ObservableField<FetchDataResult> = ObservableField()
    var errorMessage = ""
    open var api = ""
    open val isLocalData = false

    fun gson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    }
}
