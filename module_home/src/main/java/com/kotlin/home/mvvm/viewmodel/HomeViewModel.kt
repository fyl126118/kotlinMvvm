package com.kotlin.home.mvvm.viewmodel

import com.fortunes.commonsdk.network.bean.BaseBean
import com.guoyang.recyclerviewbindingadapter.observable.ObservableAdapterList
import com.kotlin.basemvvm.base.BaseVMModel
import com.kotlin.basemvvm.helper.extens.ObservableItemField
import com.kotlin.basemvvm.helper.extens.async
import com.kotlin.basemvvm.helper.network.EmptyException
import com.kotlin.home.mvvm.bean.HomeBean
import com.kotlin.home.mvvm.bean.SubData
import com.kotlin.home.mvvm.model.HomeModel
import io.reactivex.Single

/**
 * @FileName: HomeViewModel.java
 * @author: villa_mou
 * @date: 06-16:19
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class HomeViewModel : BaseVMModel<HomeModel>() {
    override var mModel: HomeModel = HomeModel()
    private var page = 1
    private var page1 = 1

    fun getProjectList(isRefresh: Boolean, cid: Int): Single<BaseBean<HomeBean>> {
        return mModel.getProjectList(
                if (isRefresh) {
                    page = 1
                    page
                } else page, cid
        )
                .async()
                .doOnSuccess{
                    if (it.data.datas.isNotEmpty()) {
                        page++
                    } else {
                        throw EmptyException()
                    }
                }
    }

    fun getProjectMineList(isRefresh: Boolean, cid: Int): Single<BaseBean<HomeBean>> {
        return mModel.getProjectList(
                if (isRefresh) {
                    page1 = 1
                    page1
                } else page1, cid
        )
                .async()
                .doOnSuccess {
                    if (it.data.datas.isNotEmpty()) {
                        page++
                    } else {
                        throw EmptyException()
                    }
                }
    }
}