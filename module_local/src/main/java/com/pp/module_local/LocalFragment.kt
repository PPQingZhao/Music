package com.pp.module_local

import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.base.ThemeFragment
import com.pp.library_router_service.services.RouterPath
import com.pp.module_local.databinding.FragmentLocalBinding

@Route(path = RouterPath.Local.fragment_local)
class LocalFragment : ThemeFragment<FragmentLocalBinding, LocalViewModel>() {
    override val mBinding: FragmentLocalBinding by lazy {
        FragmentLocalBinding.inflate(
            layoutInflater
        )
    }

    override fun getModelClazz(): Class<LocalViewModel> {
        return LocalViewModel::class.java
    }
}