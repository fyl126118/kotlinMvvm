package com.kotlin.mvvm.di.mvvm.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ScreenUtils
import com.fortunes.commonsdk.core.RouterConstants
import com.fortunes.commonsdk.network.onHttpSubscribe
import com.fortunes.commonsdk.network.onHttpSubscribeNoToast
import com.fortunes.commonsdk.utils.NavigationUtils
import com.fortunes.commonsdk.utils.perimission.EasyPermissionHelper
import com.kotlin.basemvvm.base.BaseActivity
import com.kotlin.basemvvm.helper.extens.bindDialogOrLifeCycle
import com.kotlin.basemvvm.helper.extens.bindStatusOrLifeCycle
import com.kotlin.basemvvm.helper.extens.toast
import com.kotlin.basemvvm.helper.listener.RefreshPresenter
import com.kotlin.basemvvm.widget.dialog.DialogHelper
import com.kotlin.mine.mvvm.viewmodel.MineViewModel
import com.kotlin.mvvm.R
import com.kotlin.mvvm.databinding.ActivityMainBinding
import com.kotlin.mvvm.di.mvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


@Route(path = RouterConstants.MAIN_ACTIVITY)
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), RefreshPresenter {

    override fun loadData(isRefresh: Boolean) {
        mViewModel
                .getArticle()
                //  .bindDialogOrLifeCycle(this)
                .bindStatusOrLifeCycle(isRefresh, viewModel = mViewModel, owner = this@MainActivity)
                .onHttpSubscribeNoToast(this)
                {
                    toast("成功" + it.errorMsg)
                }
    }

    override fun providerVMClass() = MainViewModel::class.java
    override fun getLayoutId() = R.layout.activity_main
    override fun initView() {
        //设置viewModel
        mBinding.apply {
            vm = mViewModel
            this.refresh = this@MainActivity
        }
        mBinding.btn.setOnClickListener {
            loadData(true)
        }
        mBinding.btnLogin.setOnClickListener {
            NavigationUtils.goLoginActivity()
        }

        mBinding.btnMine.setOnClickListener {
            NavigationUtils.goMineActivity()
        }
    }

    override fun initData() {
//        EasyPermissionHelper.requestAll(this, grantedListener = {
//
//        }, deniedListener = {
//
//        })
    }

}

