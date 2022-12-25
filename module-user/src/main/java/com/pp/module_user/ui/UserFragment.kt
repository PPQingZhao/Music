package com.pp.module_user.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.base.ThemeFragment
import com.pp.library_base.datastore.PreferenceTheme
import com.pp.library_base.datastore.setPreferenceTheme
import com.pp.library_router_service.services.RouterPath
import com.pp.module_user.databinding.FragmentUserBinding
import kotlinx.coroutines.launch

@Route(path = RouterPath.User.fragment_user)
class UserFragment : ThemeFragment<FragmentUserBinding, UserViewModel>() {
    override val mBinding: FragmentUserBinding by lazy {
        FragmentUserBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<UserViewModel> {
        return UserViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.btnTheme.setOnClickListener {
            onTheme()
        }
    }

    var flag = 0
    fun onTheme() {
        LoginAndRegisterActivity.start(requireActivity())

        lifecycleScope.launch {
            when (flag % 3) {
                0 -> {
                    context?.setPreferenceTheme(PreferenceTheme.SIMPLE_BLUE)
                }
                1 -> {
                    context?.setPreferenceTheme(PreferenceTheme.SIMPLE_NIGHT)
                }
                else -> {
                    context?.setPreferenceTheme(PreferenceTheme.SIMPLE_DEFAULT)
                }
            }
        }

        flag++
    }
}