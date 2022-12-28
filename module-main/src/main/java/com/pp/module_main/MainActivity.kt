package com.pp.module_main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
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
        initFragments()
    }

    private fun initFragments() {
        val localFragment =
            ARouter.getInstance().build(RouterPath.Local.fragment_local).navigation() as Fragment
        val mainFragment = MainFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_user, localFragment)
            .replace(R.id.fl_main, mainFragment)
            .commitNow()
    }

    override fun onStop() {
        super.onStop()
        mBinding.motionLayout.progress = 0f
    }

}