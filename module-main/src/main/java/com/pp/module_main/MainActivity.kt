package com.pp.module_main

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_base.base.ThemeActivity
import com.pp.library_base.base.ThemeViewModel
import com.pp.library_router_service.services.RouterPath
import com.pp.module_main.databinding.ActivityMainBinding

class MainActivity : ThemeActivity<ActivityMainBinding, ThemeViewModel>() {
    override val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<ThemeViewModel> {
        return ThemeViewModel::class.java
    }

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSlideView()
    }

    private fun initSlideView() {
        mBinding.slide.sliderFadeColor = Color.TRANSPARENT
        mBinding.slide.setPanelSlideListener(object : SlidingPaneLayout.SimplePanelSlideListener() {
            override fun onPanelSlide(panel: View, slideOffset: Float) {
            }
        })
    }

    fun onLogin(view: View) {
        ARouter.getInstance().build(RouterPath.User.activity_login).navigation()
    }
}