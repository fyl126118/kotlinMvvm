package com.kotlin.web.mvvm.view

import android.graphics.Bitmap
import android.graphics.Color
import android.net.http.SslError
import android.view.View
import android.webkit.*
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.fortunes.commonsdk.core.RouterConstants
import com.fortunes.commonsdk.network.HttpUrlConstants.getBaseUrl
import com.fortunes.commonsdk.network.onHttpSubscribeNoToast
import com.fortunes.commonsdk.utils.NavigationUtils
import com.guoyang.recyclerviewbindingadapter.ItemClickPresenter
import com.guoyang.recyclerviewbindingadapter.adapter.SingleTypeAdapter
import com.kotlin.basemvvm.base.BaseActivity
import com.kotlin.basemvvm.helper.extens.bindStatusOrLifeCycle
import com.kotlin.basemvvm.helper.extens.toast
import com.kotlin.basemvvm.helper.listener.RefreshPresenter
import com.kotlin.web.R
import com.kotlin.web.databinding.WebActivityWebBinding
import com.kotlin.web.mvvm.viewmodel.WebItemViewModel
import com.kotlin.web.mvvm.viewmodel.WebViewModel
import com.noober.background.drawable.DrawableCreator
import timber.log.Timber


/**
 * @FileName: WebActivity.java
 * @author:
 * @date: 06-16:18
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
@Route(path = RouterConstants.WEB_ACTIVITY)
class WebActivity : BaseActivity<WebActivityWebBinding, WebViewModel>(), RefreshPresenter, ItemClickPresenter<WebItemViewModel> {

    @Autowired(name = NavigationUtils.WEB_URL)
    @JvmField
    var webUrl: String = ""

    @Autowired(name = NavigationUtils.WEB_TITLE)
    @JvmField
    var title: String? = ""

    override fun providerVMClass() = WebViewModel::class.java
    override fun getLayoutId() = R.layout.web_activity_web
    override fun loadData(isRefresh: Boolean) = loadVMData(isRefresh)
    override fun initData() = loadVMData(true)
    private val mAdapter by lazy {
        SingleTypeAdapter(this, R.layout.webview_item_order, mViewModel.observableList)
                .apply { this.itemPresenter = this@WebActivity }
    }


    override fun initView() {
        ARouter.getInstance().inject(this@WebActivity)
        mBinding.apply {
            viewModel = mViewModel
            this.refreshPresenter = this@WebActivity
            recyclerView.adapter = mAdapter
        }
        val drawable = DrawableCreator.Builder()
                .setShape(DrawableCreator.Shape.Rectangle)
                .setCornersRadius(0f, 40f, 40f, 0f)
                .setGradientAngle(0)
                .setGradientColor(Color.parseColor("#D73411"), Color.parseColor("#70A4CD"))
                .build()
        mBinding.tvPay.background = drawable
        mBinding.wvLoad.loadUrl(webUrl)
        mBinding.wvLoad.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Timber.e(webUrl)
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
                if (view?.url!!.contains(getBaseUrl())) {
                    //todo 暂时取消验证
                } else {
                    super.onReceivedSslError(view, handler, error)
                }

            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                Timber.e(webUrl)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Timber.e(webUrl)
            }
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