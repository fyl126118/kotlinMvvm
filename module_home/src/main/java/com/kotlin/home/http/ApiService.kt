package com.kotlin.home.http

import com.fortunes.commonsdk.network.bean.BaseBean
import com.kotlin.home.mvvm.bean.HomeBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("project/list/{pageNum}/json")
    fun getProjectList(@Path("pageNum") pageNum: Int, @Query("cid") cid: Int): Single<BaseBean<HomeBean>>
}