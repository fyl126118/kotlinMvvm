package com.kotlin.home.mvvm.model

import com.kotlin.basemvvm.base.BaseModel
import com.kotlin.home.http.HomeApiManager

/**
 * @FileName: LoginModel.java
 * @author: villa_mou
 * @date: 08-11:09
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class HomeModel:BaseModel() {
    fun getProjectList(pageNum:Int,cid:Int)=HomeApiManager.apiService.getProjectList(pageNum,cid)
}