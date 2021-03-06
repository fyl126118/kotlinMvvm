package com.kotlin.mine.mvvm.model

import com.kotlin.basemvvm.base.BaseModel
import com.kotlin.mine.http.MineApiManager

/**
 * @FileName: MineModel.java
 * @author: villa_mou
 * @date: 08-11:12
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class MineModel:BaseModel() {
    fun getProjectList(pageNum:Int,cid:Int)=MineApiManager.apiService.getProjectList(pageNum,cid)
}