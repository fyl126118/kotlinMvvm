package com.kotlin.mine.mvvm.view

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
import com.kotlin.mine.R
import com.kotlin.mine.databinding.MineActivityMineBinding
import com.kotlin.mine.mvvm.viewmodel.MineItemViewModel
import com.kotlin.mine.mvvm.viewmodel.MineViewModel

/**
 * @FileName: LoginActivity.java
 * @author: villa_mou
 * @date: 06-16:18
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
@Route(path = RouterConstants.MINE_ACTIVITY)
class MineActivity : BaseActivity<MineActivityMineBinding,MineViewModel>(), RefreshPresenter, ItemClickPresenter<MineItemViewModel> {
    override fun providerVMClass()=MineViewModel::class.java
    override fun getLayoutId() = R.layout.mine_activity_mine
    override fun loadData(isRefresh: Boolean) = loadVMData(isRefresh)
    override fun initData() = loadVMData(true)
    private val mAdapter by lazy {
        SingleTypeAdapter(this, R.layout.mine_my_item_order, mViewModel.observableList)
            .apply { this.itemPresenter = this@MineActivity }
    }

    override fun initView() {
        mBinding.apply {
            viewModel = mViewModel
            this.refreshPresenter = this@MineActivity
            recyclerView.adapter = mAdapter
        }

    }

    override fun onItemClick(v: View, position: Int, item: MineItemViewModel) {
        toast("position=" + position)
    }

    private fun loadVMData(isRefresh: Boolean) =
        mViewModel.getProjectList(isRefresh, 294)
            .bindStatusOrLifeCycle(isRefresh, viewModel = mViewModel, owner = this@MineActivity)
            .onHttpSubscribeNoToast(this@MineActivity)
}