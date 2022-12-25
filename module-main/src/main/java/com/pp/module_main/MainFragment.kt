package com.pp.module_main

import com.pp.library_base.base.ThemeFragment
import com.pp.module_main.databinding.FragmentMainBinding

class MainFragment : ThemeFragment<FragmentMainBinding, MainViewModel>() {
    override val mBinding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<MainViewModel> {
        return MainViewModel::class.java
    }
}