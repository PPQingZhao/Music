package com.pp.module_local.ui

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.base.ThemeActivity
import com.pp.library_router_service.services.RouterPath
import com.pp.module_local.databinding.ActivityAllMusicsBinding
import com.pp.module_local.databinding.ActivityDownloadBinding
import com.pp.module_local.databinding.ActivityLikedBinding

@Route(path = RouterPath.Local.activity_liked)
class LikedActivity : ThemeActivity<ActivityLikedBinding, LikedViewModel>() {
    override val mBinding: ActivityLikedBinding by lazy {
        ActivityLikedBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<LikedViewModel> {
        return LikedViewModel::class.java
    }

    fun onBack(v: View) {
        onBackPressed()
    }
}