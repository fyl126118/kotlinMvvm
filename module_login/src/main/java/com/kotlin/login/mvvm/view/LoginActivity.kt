package com.kotlin.login.mvvm.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.fortunes.commonsdk.core.RouterConstants
import com.fortunes.commonsdk.utils.NavigationUtils
import com.kotlin.basemvvm.base.BaseActivity
import com.kotlin.login.R
import com.kotlin.login.databinding.LoginActivityLoginBinding
import com.kotlin.login.mvvm.viewmodel.LoginViewModel

/**
 * @FileName: LoginActivity.java
 * @author: villa_mou
 * @date: 06-16:18
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
@Route(path = RouterConstants.LOGIN_ACTIVITY)
class LoginActivity : BaseActivity<LoginActivityLoginBinding, LoginViewModel>() {
    override fun providerVMClass() = LoginViewModel::class.java
    override fun getLayoutId() = R.layout.login_activity_login

    override fun initView() {
        mBinding.btLogin.setOnClickListener {
            NavigationUtils.goWebActivity("https://www.baidu.com", "百度")
        }
    }

    override fun initData() {
    }
}