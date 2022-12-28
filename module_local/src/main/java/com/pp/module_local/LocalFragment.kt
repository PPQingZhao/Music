package com.pp.module_local

import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {

        mBinding.ivHead.setOnClickListener {
            val transitionAnimation =
                ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity())
            ARouter.getInstance()
                .build(RouterPath.User.activity_login)
                .withOptionsCompat(transitionAnimation)
                .navigation(requireActivity(),0)
        }
    }
}