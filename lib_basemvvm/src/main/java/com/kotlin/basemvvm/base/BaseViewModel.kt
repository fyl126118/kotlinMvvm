package com.kotlin.basemvvm.base

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.kotlin.basemvvm.helper.annotation.PageStateType
import com.kotlin.basemvvm.helper.annotation.RefreshType
import com.kotlin.basemvvm.helper.extens.ObservableItemField
import timber.log.Timber

/***
 *
 *   █████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 *  ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 *  ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 *  ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 *           ░     ░ ░      ░  ░
 *
 * Created by mou on 2018/8/20.
 * ViewModel的父类
 */
@SuppressLint("SupportAnnotationUsage")
abstract class BaseViewModel : ViewModel() {
    //页面状态
    @PageStateType
    val pageState = ObservableItemField<Int>()
    //刷新/加载更多状态
    @RefreshType
    val listState = ObservableItemField<Int>()

    init {
        pageState.set(PageStateType.NORMAL)
        listState.set(RefreshType.NORMAL)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("${javaClass.simpleName}:onCleared()")
    }
}