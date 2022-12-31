package com.pp.module_local.ui

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.base.ThemeActivity
import com.pp.library_router_service.services.RouterPath
import com.pp.module_local.databinding.ActivityAllMusicsBinding

@Route(path = RouterPath.Local.activity_all_musics)
class AllMusicsActivity : ThemeActivity<ActivityAllMusicsBinding, AllMusicsViewModel>() {
    override val mBinding: ActivityAllMusicsBinding by lazy {
        ActivityAllMusicsBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<AllMusicsViewModel> {
        return AllMusicsViewModel::class.java
    }

    fun onBack(v: View) {
        onBackPressed()
    }
}