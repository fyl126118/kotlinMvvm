package com.kotlin.home.mvvm.view

import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils
import com.fortunes.commonsdk.network.onHttpSubscribeNoToast
import com.guoyang.recyclerviewbindingadapter.ItemClickPresenter
import com.guoyang.recyclerviewbindingadapter.adapter.SingleTypeAdapter
import com.kotlin.basemvvm.base.BaseFragment
import com.kotlin.basemvvm.helper.extens.bindStatusOrLifeCycle
import com.kotlin.basemvvm.helper.listener.RefreshPresenter
import com.kotlin.home.R
import com.kotlin.home.databinding.HomeFragmentHomeBinding
import com.kotlin.home.mvvm.viewmodel.HomeItemViewModel
import com.kotlin.home.mvvm.viewmodel.HomeViewModel

class HomeFragment :BaseFragment<HomeFragmentHomeBinding,HomeViewModel>(), RefreshPresenter, ItemClickPresenter<HomeItemViewModel> {
    override fun providerVMClass()=HomeViewModel::class.java

    override fun getLayoutId(): Int {
        return R.layout.home_fragment_home
    }

    override fun loadData(isRefresh: Boolean) = loadVMData(isRefresh)
    override fun initData() = loadVMData(true)
    private val mHomeAdapter by lazy {
        SingleTypeAdapter(mContext, R.layout.home_item, mViewModel.observableList)
                .apply { this.itemPresenter = this@HomeFragment }
    }

    override fun initView() {
        mBinding.apply {
            homeBean = mViewModel
            this.refreshPresenter = this@HomeFragment
            recyclerView.adapter = mHomeAdapter

        }

    }

    override fun onItemClick(v: View, position: Int, item: HomeItemViewModel) {
       ToastUtils.showLong(position)
    }


    private fun loadVMData(isRefresh: Boolean) =
            mViewModel.getProjectList(isRefresh, 294)
                    .bindStatusOrLifeCycle(isRefresh, viewModel = mViewModel, owner = this@HomeFragment)
                    .onHttpSubscribeNoToast(activity@mContext){

                    }

}