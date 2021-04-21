package com.kotlin.home.mvvm.view

import com.fortunes.commonsdk.network.onHttpSubscribeNoToast
import com.kotlin.basemvvm.base.BaseFragment
import com.kotlin.basemvvm.helper.extens.bindStatusOrLifeCycle
import com.kotlin.basemvvm.helper.listener.RefreshPresenter
import com.kotlin.basemvvm.helper.network.EmptyException
import com.kotlin.home.R
import com.kotlin.home.databinding.HomeFragmentHomeBinding
import com.kotlin.home.mvvm.adapter.HomeAdapter
import com.kotlin.home.mvvm.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<HomeFragmentHomeBinding, HomeViewModel>(), RefreshPresenter {

    private var mAdapter = HomeAdapter()

    override fun providerVMClass() = HomeViewModel::class.java

    override fun getLayoutId(): Int {
        return R.layout.home_fragment_home
    }

    override fun loadData(isRefresh: Boolean) = loadVMData(isRefresh)

    override fun initData() = loadVMData(true)

    override fun initView() {
        mBinding.apply {
            homeBean = mViewModel
            this.refreshPresenter = this@HomeFragment
            recyclerView.adapter = mAdapter
        }
    }

    private fun loadVMData(isRefresh: Boolean) =
            mViewModel.getProjectList(isRefresh, 294)
                    .bindStatusOrLifeCycle(isRefresh, viewModel = mViewModel, owner = this@HomeFragment)
                    .onHttpSubscribeNoToast(activity@ mContext) {
                        if (isRefresh) {
                            mAdapter.setList(it.data.datas)
                        } else {
                            mAdapter.addData(it.data.datas)
                        }
                    }

}