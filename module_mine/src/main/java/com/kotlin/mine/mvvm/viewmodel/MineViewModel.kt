package com.kotlin.mine.mvvm.viewmodel

import com.fortunes.commonsdk.network.bean.BaseBean
import com.guoyang.recyclerviewbindingadapter.observable.ObservableAdapterList
import com.kotlin.basemvvm.base.BaseVMModel
import com.kotlin.basemvvm.helper.extens.ObservableItemField
import com.kotlin.basemvvm.helper.extens.async
import com.kotlin.basemvvm.helper.network.EmptyException
import com.kotlin.mine.mvvm.bean.MineBean
import com.kotlin.mine.mvvm.bean.SubData
import com.kotlin.mine.mvvm.model.MineModel
import io.reactivex.Single

/**
 * @FileName: MineViewModel.java
 * @author: villa_mou
 * @date: 06-16:19
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class MineViewModel : BaseVMModel<MineModel>() {
    override var mModel: MineModel=MineModel()
    private var page = 1
    val observableList = ObservableAdapterList<MineItemViewModel>()
    fun getProjectList(isRefresh: Boolean, cid: Int): Single<BaseBean<MineBean>> {
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
                    val list = mutableListOf<MineItemViewModel>()
                    it.data.datas.forEach { orderBean: SubData ->
                        list.add(MineItemViewModel(orderBean))
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

class MineItemViewModel(bean: SubData) {
    val chapterName = ObservableItemField<String>()
    val desc = ObservableItemField<String>()
    val data = bean

    init {
        chapterName.set(bean.chapterName)
        desc.set(bean.desc)
    }
}