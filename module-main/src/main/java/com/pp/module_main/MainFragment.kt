package com.pp.module_main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.base.ThemeFragment
import com.pp.module_main.databinding.FragmentMainBinding

class MainFragment : ThemeFragment<FragmentMainBinding, MainViewModel>() {
    override val mBinding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mBinding.recyclerview.layoutManager = LinearLayoutManager(context)
    }
}