package com.kotlin.mine.mvvm.viewmodel

import com.fortunes.commonsdk.network.bean.BaseBean
import com.guoyang.recyclerviewbindingadapter.observable.ObservableAdapterList
import com.kotlin.basemvvm.base.BaseVMModel
import com.kotlin.basemvvm.helper.extens.ObservableItemField
import com.kotlin.basemvvm.helper.extens.async
import com.kotlin.basemvvm.helper.network.EmptyException
import com.kotlin.web.mvvm.bean.SubData
import com.kotlin.web.mvvm.bean.WebViewBean
import com.kotlin.web.mvvm.model.WebModel
import io.reactivex.Single

/**
 * @FileName: MineViewModel.java
 * @author: villa_mou
 * @date: 06-16:19
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class WebViewModel : BaseVMModel<WebModel>() {
    override var mModel: WebModel=WebModel()
    private var page = 1
    val observableList = ObservableAdapterList<WebItemViewModel>()
    fun getProjectList(isRefresh: Boolean, cid: Int): Single<BaseBean<WebViewBean>> {
        return mModel
            .getProjectList(
                if (isRefresh) {
                    page = 1
                    page
                } else page, cid
            )
            .async()
            .doOnSuccess {
                if (it.data.datas.isNotEmpty()) {
                    val list = mutableListOf<WebItemViewModel>()
                    it.data.datas.forEach { orderBean: SubData ->
                        list.add(WebItemViewModel(orderBean))
                    }
                    page++
                    if (isRefresh) {
                        observableList.setNewData(list)
                    } else {
                        observableList.addAll(list)
                    }
                } else {
                    throw EmptyException()
                }
            }
    }
}

class WebItemViewModel(bean: SubData) {
    val chapterName = ObservableItemField<String>()
    val desc = ObservableItemField<String>()
    val data = bean
    init {
        chapterName.set(bean.chapterName)
        desc.set(bean.desc)
    }
}