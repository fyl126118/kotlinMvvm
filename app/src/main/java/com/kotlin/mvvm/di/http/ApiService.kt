package com.kotlin.mvvm.di.http

import com.fortunes.commonsdk.network.bean.BaseBean
import com.kotlin.mvvm.di.mvvm.bean.ArticleBean
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    /**
     * 判断是否上线
     */
    @GET("article/list/1/json")
    fun getArticle(): Single<BaseBean<ArticleBean>>
}