package com.pp.module_local.ui

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.base.ThemeActivity
import com.pp.library_router_service.services.RouterPath
import com.pp.module_local.databinding.ActivityAllMusicsBinding
import com.pp.module_local.databinding.ActivityDownloadBinding
import com.pp.module_local.databinding.ActivityRecentlyPlayedBinding

@Route(path = RouterPath.Local.activity_recently_played)
class RecentlyPlayedActivity :
    ThemeActivity<ActivityRecentlyPlayedBinding, RecentlyPlayedViewModel>() {
    override val mBinding: ActivityRecentlyPlayedBinding by lazy {
        ActivityRecentlyPlayedBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<RecentlyPlayedViewModel> {
        return RecentlyPlayedViewModel::class.java
    }

    fun onBack(v: View) {
        onBackPressed()
    }
}