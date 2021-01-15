package com.kotlin.mine.http

import com.fortunes.commonsdk.network.HttpUrlConstants
import com.fortunes.commonsdk.network.provider.BaseNetProvider
import com.kotlin.basemvvm.base.BaseApplication
import com.kotlin.basemvvm.helper.network.NetMgr

/**
 * @FileName: MineApiManager.java
 * @author: villa_mou
 * @date: 08-10:52
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
object MineApiManager {
    val apiService by lazy {
        NetMgr.getRetrofit(
                HttpUrlConstants.getBaseUrl(),
                BaseNetProvider(BaseApplication.instance())
        ).create(ApiService::class.java)
    }
}