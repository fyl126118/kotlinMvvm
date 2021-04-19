package com.kotlin.basemvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.basemvvm.widget.LoadDialog
import com.noober.background.BackgroundLibrary

/***
 * Created by mou on 2018/8/20.
 * Activity的父类
 */

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : AppCompatActivity(), IView, IActivity{
    protected lateinit var mBinding: B
    lateinit var mViewModel: VM
    abstract fun providerVMClass(): Class<VM>?
    private val progressDialog: LoadDialog by lazy {
        LoadDialog.create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        BackgroundLibrary.inject(this)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        super.onCreate(savedInstanceState)
        mBinding.lifecycleOwner = this
        initVM()
        initView()
        initData()
    }
    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(it)
        }
    }

    override fun showLoading(message: String) {
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    override fun hideLoading() {
        progressDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog.dismiss()
    }
}