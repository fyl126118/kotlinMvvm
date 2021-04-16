package com.kotlin.web.mvvm.view

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.fortunes.commonsdk.core.RouterConstants
import com.fortunes.commonsdk.network.onHttpSubscribeNoToast
import com.guoyang.recyclerviewbindingadapter.ItemClickPresenter
import com.guoyang.recyclerviewbindingadapter.adapter.SingleTypeAdapter
import com.kotlin.basemvvm.base.BaseActivity
import com.kotlin.basemvvm.helper.extens.bindStatusOrLifeCycle
import com.kotlin.basemvvm.helper.extens.toast
import com.kotlin.basemvvm.helper.listener.RefreshPresenter
import com.kotlin.mine.mvvm.viewmodel.WebItemViewModel
import com.kotlin.mine.mvvm.viewmodel.WebViewModel
import com.kotlin.web.R
import com.kotlin.web.databinding.WebActivityWebBinding

/**
 * @FileName: WebActivity.java
 * @author: villa_mou
 * @date: 06-16:18
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
@Route(path = RouterConstants.WEB_ACTIVITY)
class WebActivity : BaseActivity<WebActivityWebBinding,WebViewModel>(), RefreshPresenter, ItemClickPresenter<WebItemViewModel> {
    override fun providerVMClass()=WebViewModel::class.java
    override fun getLayoutId() = R.layout.web_activity_web
    override fun loadData(isRefresh: Boolean) = loadVMData(isRefresh)
    override fun initData() = loadVMData(true)
    private val mAdapter by lazy {
        SingleTypeAdapter(this, R.layout.webview_item_order, mViewModel.observableList)
            .apply { this.itemPresenter = this@WebActivity }
    }

    override fun initView() {
        mBinding.apply {
            viewModel = mViewModel
            this.refreshPresenter = this@WebActivity
            recyclerView.adapter = mAdapter
        }

    }

    override fun onItemClick(v: View, position: Int, item: WebItemViewModel) {
        toast("position=" + position)
    }

    private fun loadVMData(isRefresh: Boolean) =
        mViewModel.getProjectList(isRefresh, 294)
            .bindStatusOrLifeCycle(isRefresh, viewModel = mViewModel, owner = this@WebActivity)
            .onHttpSubscribeNoToast(this@WebActivity)
}