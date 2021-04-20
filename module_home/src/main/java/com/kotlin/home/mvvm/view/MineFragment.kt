package com.kotlin.home.mvvm.view

import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.fortunes.commonsdk.network.onHttpSubscribeNoToast
import com.guoyang.recyclerviewbindingadapter.ItemClickPresenter
import com.guoyang.recyclerviewbindingadapter.adapter.SingleTypeAdapter
import com.kotlin.basemvvm.base.BaseFragment
import com.kotlin.basemvvm.helper.extens.bindStatusOrLifeCycle
import com.kotlin.basemvvm.helper.listener.RefreshPresenter
import com.kotlin.home.R
import com.kotlin.home.databinding.HomeFragmentMineBinding
import com.kotlin.home.mvvm.viewmodel.HomeItemViewModel
import com.kotlin.home.mvvm.viewmodel.HomeViewModel

class MineFragment :BaseFragment<HomeFragmentMineBinding,HomeViewModel>(), RefreshPresenter, ItemClickPresenter<HomeItemViewModel> {

    override fun providerVMClass()=HomeViewModel::class.java


    override fun getLayoutId(): Int {
        return R.layout.home_fragment_mine
    }

    override fun loadData(isRefresh: Boolean) = loadVMData(isRefresh)
    override fun initData() = loadVMData(true)
    private val mAdapter by lazy {
        SingleTypeAdapter(mContext, R.layout.home_item, mViewModel.observableList1)
                .apply { this.itemPresenter = this@MineFragment }
    }


    override fun initView() {
        mBinding.apply {
            homeBean = mViewModel
            this.refreshPresenter = this@MineFragment
            recyclerView.adapter = mAdapter
        }

    }


    override fun onItemClick(v: View, position: Int, item: HomeItemViewModel) {
        ToastUtils.showLong(position)
    }

    private fun loadVMData(isRefresh: Boolean) =
            mViewModel.getProjectMineList(isRefresh, 290)
                    .bindStatusOrLifeCycle(isRefresh, viewModel = mViewModel, owner = this@MineFragment)
                    .onHttpSubscribeNoToast(activity@mContext)

}