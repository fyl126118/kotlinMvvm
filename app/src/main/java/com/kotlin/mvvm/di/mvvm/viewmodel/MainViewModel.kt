package com.kotlin.mvvm.di.mvvm.viewmodel


import com.fortunes.commonsdk.network.bean.BaseBean
import com.kotlin.basemvvm.base.BaseVMModel
import com.kotlin.basemvvm.helper.annotation.PageStateType
import com.kotlin.basemvvm.helper.extens.ObservableItemField
import com.kotlin.basemvvm.helper.extens.async
import com.kotlin.mvvm.di.mvvm.bean.ArticleBean
import com.kotlin.mvvm.di.mvvm.model.MainModel
import io.reactivex.Single
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException


class MainViewModel : BaseVMModel<MainModel>() {
    override var mModel: MainModel = MainModel()
    val chapterName = ObservableItemField<String>()
    val link = ObservableItemField<String>()
    fun getArticle(): Single<BaseBean<ArticleBean>> {
        return mModel
                .getArticle()
                .async()
                .doOnSuccess {
                    chapterName.set(it.data.datas[0].chapterName)
                    link.set(it.data.datas[0].link)
                }
                .doOnError {
                    Timber.d("doOnError")
                    //页面状
                    if (it is UnknownHostException || it is ConnectException) {
                        pageState.set(PageStateType.NOWORK)
                    } else {
                        pageState.set(PageStateType.ERROR)
                    }
                }

    }
}

