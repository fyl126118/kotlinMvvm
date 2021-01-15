package com.kotlin.mvvm.di.mvvm.model

import com.kotlin.basemvvm.base.BaseModel
import com.kotlin.mvvm.di.http.HomeApiManager

/**
 * @FileName: MainModel.java
 * @author: villa_mou
 * @date: 08-10:49
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class MainModel:BaseModel() {
    fun  getArticle()= HomeApiManager.apiService.getArticle()
}