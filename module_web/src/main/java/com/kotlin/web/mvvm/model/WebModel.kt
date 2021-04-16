package com.kotlin.web.mvvm.model

import com.kotlin.basemvvm.base.BaseModel
import com.kotlin.web.http.WebViewApiManager

/**
 * @FileName: WebModel.java
 * @author: villa_mou
 * @date: 08-11:12
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class WebModel:BaseModel() {
    fun getProjectList(pageNum:Int,cid:Int)=WebViewApiManager.apiService.getProjectList(pageNum,cid)
}