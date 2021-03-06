package com.kotlin.web.http

import com.fortunes.commonsdk.network.bean.BaseBean
import com.kotlin.web.mvvm.bean.WebViewBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("project/list/{pageNum}/json")
    fun getProjectList(@Path("pageNum") pageNum: Int, @Query("cid") cid: Int): Single<BaseBean<WebViewBean>>
}