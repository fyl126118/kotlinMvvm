package com.kotlin.home.mvvm.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.kotlin.home.R
import com.kotlin.home.databinding.HomeItemBinding
import com.kotlin.home.mvvm.bean.SubData

class HomeAdapter : BaseQuickAdapter<SubData, BaseDataBindingHolder<HomeItemBinding>>(R.layout.home_item) {

    private val mPresenter = HomePresenter()

    override fun convert(holder: BaseDataBindingHolder<HomeItemBinding>, item: SubData) {
        if (item == null) {
            return
        }
        // 获取 Binding
        val binding = holder.dataBinding
        if (binding != null) {
            binding.item = item
            binding.presenter = mPresenter
            binding.executePendingBindings()
        }
    }
}