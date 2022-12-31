package com.pp.module_local.ui

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.base.ThemeActivity
import com.pp.library_router_service.services.RouterPath
import com.pp.module_local.databinding.ActivityThemeSettingBinding

@Route(path = RouterPath.Local.activity_them_setting)
class ThemeSettingActivity : ThemeActivity<ActivityThemeSettingBinding, ThemeSettingViewModel>() {
    override val mBinding: ActivityThemeSettingBinding by lazy {
        ActivityThemeSettingBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<ThemeSettingViewModel> {
        return ThemeSettingViewModel::class.java
    }

    fun onBack(v: View) {
        onBackPressed()
    }
}