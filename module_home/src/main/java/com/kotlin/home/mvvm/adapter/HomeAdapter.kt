package com.kotlin.home.mvvm.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.guoyang.recyclerviewbindingadapter.adapter.BindingViewHolder
import com.guoyang.recyclerviewbindingadapter.adapter.SingleTypeAdapter
import com.guoyang.recyclerviewbindingadapter.observable.ObservableAdapterList
import com.kotlin.home.mvvm.bean.HomeBean

class HomeAdapter(context: Context, layoutRes: Int, list: ObservableAdapterList<HomeBean>) : SingleTypeAdapter<HomeBean>(context, layoutRes, list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ViewDataBinding> {
        return super.onCreateViewHolder(parent, viewType)

    }
}