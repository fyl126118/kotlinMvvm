package com.kotlin.home.mvvm.adapter

import android.view.Gravity
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.kotlin.home.R
import com.kotlin.home.mvvm.bean.SubData

class HomePresenter {
    fun buyTicket(view: View?, subData: SubData) {
        ToastUtils.make()
                .setMode(ToastUtils.MODE.DARK)
                .setGravity(Gravity.CENTER, 0, 120)
                .setTopIcon(R.drawable.ic_main_tab_1_selected)
                .show("buy ticket: ")
    }
}