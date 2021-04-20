package com.kotlin.home.mvvm.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.fortunes.commonsdk.core.RouterConstants
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kotlin.basemvvm.base.BaseActivity
import com.kotlin.home.R
import com.kotlin.home.databinding.HomeActivityHomeBinding
import com.kotlin.home.mvvm.viewmodel.HomeViewModel
import timber.log.Timber

/**
 * @FileName: HomeActivity.java
 * @author: villa_mou
 * @date: 06-16:18
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
@Route(path = RouterConstants.HOME_ACTIVITY)
class HomeActivity : BaseActivity<HomeActivityHomeBinding, HomeViewModel>() {

    private var fragments: ArrayList<Fragment>? = null
    private var mPosition = 0
    private var lastPosition = 0
    private var currentFragment: Fragment? = null
    private var hideFragment: Fragment? = null
    override fun providerVMClass() = HomeViewModel::class.java
    override fun getLayoutId() = R.layout.home_activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragments = ArrayList<Fragment>()
        fragments!!.add(HomeFragment())
        fragments!!.add(MineFragment())
        if (savedInstanceState == null) {
            //根据传入的Bundle对象判断是正常启动还是重建 true表示正常启动，false表示重建
            setSelectedFragment(0)
        }

        var navView = mBinding.navView
        navView.itemIconTintList = null
        navView.setOnNavigationItemSelectedListener { item: android.view.MenuItem? ->
            if (item?.itemId == R.id.navigation_home) {
                setSelectedFragment(0)
            } else if (item?.itemId== R.id.navigation_dashboard) {
                setSelectedFragment(1)
            }
            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Timber.d("onSaveInstanceState: ")
        outState.putInt("last_position", lastPosition) //activity重建时保存页面的位置

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.d("onRestoreInstanceState: ")
        lastPosition = savedInstanceState.getInt("last_position") //获取重建时的fragment的位置
        setSelectedFragment(lastPosition)
        mBinding.navView.selectedItemId = mBinding.navView.menu.getItem(lastPosition).itemId
    }
    override fun initView() {

    }

    override fun initData() {
    }

    private fun setSelectedFragment(position: Int) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        currentFragment = fragmentManager.findFragmentByTag("fragment$position")
        hideFragment = fragmentManager.findFragmentByTag("fragment$lastPosition")
        if (position != lastPosition) {
            if (hideFragment != null) {
                transaction.hide(hideFragment!!)
            }
            if (currentFragment == null) {
                currentFragment = fragments!![position]
                transaction.add(R.id.nav_host_fragment, currentFragment!!, "fragment$position")
            } else {
                transaction.show(currentFragment!!)
            }
        }
        if (position == lastPosition) {
            if (currentFragment == null) {
                currentFragment = fragments!![position]
                transaction.add(R.id.nav_host_fragment, currentFragment!!, "fragment$position")
            }
        }
//        if (currentFragment is FragmentBar) {
//            (currentFragment as FragmentBar).setBar()
//        }
        transaction.commit()
        lastPosition = position //更新要隐藏的fragment的位置
        mPosition = position
    }
}