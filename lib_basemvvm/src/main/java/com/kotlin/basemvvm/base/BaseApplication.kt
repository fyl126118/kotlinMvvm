package com.kotlin.basemvvm.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.kotlin.basemvvm.integration.AppDelegate
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout


/***
 * Created by mou on 2018/8/20.
 * 通用的Application
 */

abstract class BaseApplication : Application() {
    private var mAppDelegate: AppDelegate? = null

    companion object {
        private var instance: Application? = null
        fun instance() = instance ?: throw Throwable("instance 还未初始化完成")
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        if (mAppDelegate == null) {
            mAppDelegate = AppDelegate(base)
        }
        mAppDelegate?.attachBaseContext(base)
        //static 代码段可以防止内存泄露

    }
    init {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context: Context?, layout: RefreshLayout? ->
            layout?.setEnableOverScrollBounce(false)
            layout?.setDragRate(1f)
            ClassicsHeader(context) //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context?, layout: RefreshLayout? ->
            layout?.setEnableAutoLoadMore(false)
            layout?.setDragRate(1f)
            ClassicsFooter(context)
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        mAppDelegate?.onCreate(this)

    }

    override fun onTerminate() {
        super.onTerminate()
        mAppDelegate?.onTerminate(this)
    }
}