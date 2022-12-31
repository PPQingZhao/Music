package com.pp.module_local.ui

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.base.ThemeActivity
import com.pp.library_router_service.services.RouterPath
import com.pp.module_local.databinding.ActivityAllMusicsBinding
import com.pp.module_local.databinding.ActivityDownloadBinding

@Route(path = RouterPath.Local.activity_download)
class DownloadActivity : ThemeActivity<ActivityDownloadBinding, DownloadViewModel>() {
    override val mBinding: ActivityDownloadBinding by lazy {
        ActivityDownloadBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<DownloadViewModel> {
        return DownloadViewModel::class.java
    }

    fun onBack(v: View) {
        onBackPressed()
    }
}