package com.kotlin.mvvm.di.mvvm.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.*
import com.fortunes.commonsdk.bean.UserInfoBean
import com.fortunes.commonsdk.core.RouterConstants
import com.fortunes.commonsdk.network.onHttpSubscribe
import com.fortunes.commonsdk.network.onHttpSubscribeNoToast
import com.fortunes.commonsdk.utils.NavigationUtils
import com.fortunes.commonsdk.utils.perimission.EasyPermissionHelper
import com.fortunes.commonsdk.utils.sp.EasySharedPreferences
import com.fortunes.commonsdk.utils.sp.PreferenceSupport
import com.kotlin.basemvvm.base.BaseActivity
import com.kotlin.basemvvm.helper.extens.bindDialogOrLifeCycle
import com.kotlin.basemvvm.helper.extens.bindStatusOrLifeCycle
import com.kotlin.basemvvm.helper.extens.toast
import com.kotlin.basemvvm.helper.listener.RefreshPresenter
import com.kotlin.basemvvm.widget.dialog.DialogHelper
import com.kotlin.mvvm.R
import com.kotlin.mvvm.databinding.ActivityMainBinding
import com.kotlin.mvvm.di.mvvm.viewmodel.MainViewModel


@Route(path = RouterConstants.MAIN_ACTIVITY)
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), RefreshPresenter {

    override fun loadData(isRefresh: Boolean) {
        mViewModel
                .getArticle()
                //  .bindDialogOrLifeCycle(this)
                .bindStatusOrLifeCycle(isRefresh, viewModel = mViewModel, owner = this@MainActivity)
                .onHttpSubscribeNoToast(this)
                {
//                    var userInfoBean = UserInfoBean("16666666", "王菲")
//                    EasySharedPreferences.
//                    SPUtils.getInstance("userinfo").put("mobile",userInfoBean.mobile)
//                    SPUtils.getInstance().put("userinfo",userInfoBean.name)
                    val user = EasySharedPreferences.load(UserInfoBean::class.java)
                    user.mobile="888888888"
                    user.name="w99999"
                    user.apply()
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

        mBinding.btnHome.setOnClickListener {
            NavigationUtils.goHomeActivity()
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

