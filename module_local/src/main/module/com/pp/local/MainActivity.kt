package com.pp.local

import com.pp.library_base.base.ThemeActivity
import com.pp.library_base.base.ThemeViewModel
import com.pp.module_local.databinding.ActivityModuleMainBinding

class MainActivity : ThemeActivity<ActivityModuleMainBinding,ThemeViewModel>() {

    override val mBinding: ActivityModuleMainBinding by lazy {
        ActivityModuleMainBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<ThemeViewModel> {
        return ThemeViewModel::class.java
    }
}