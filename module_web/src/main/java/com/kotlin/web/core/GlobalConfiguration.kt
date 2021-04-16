package com.kotlin.web.core

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.kotlin.basemvvm.integration.AppLifeCycles
import com.kotlin.basemvvm.integration.ConfigModule
import timber.log.Timber

/***
 * 该类是整个module可以对整个App的Application/Activity/Fragment的生命周期进行逻辑注入
 * 例如我们平时的第三方代码就可以在这里去进行实现
 **/

class GlobalConfiguration : ConfigModule {
    override fun injectAppLifecycle(context: Context, lifeCycles: MutableList<AppLifeCycles>) {
        Timber.e("1")
    }

    override fun injectActivityLifecycle(context: Context, lifeCycles: MutableList<Application.ActivityLifecycleCallbacks>) {
        Timber.e("1")
    }

    override fun injectFragmentLifecycle(context: Context, lifeCycles: MutableList<FragmentManager.FragmentLifecycleCallbacks>) {
        Timber.e("1")
    }
}
