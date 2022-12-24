package com.pp.module_user.ui

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.base.ThemeActivity
import com.pp.library_base.base.ThemeViewModel
import com.pp.library_base.datastore.PreferenceTheme
import com.pp.library_base.datastore.setPreferenceTheme
import com.pp.library_router_service.services.RouterPath
import com.pp.module_user.databinding.ActivityLoginAndRegisterBinding
import kotlinx.coroutines.launch

@Route(path = RouterPath.User.activity_login)
class LoginAndRegisterActivity : ThemeActivity<ActivityLoginAndRegisterBinding, LoginAndRegisterViewModel>() {
    override val mBinding: ActivityLoginAndRegisterBinding by lazy {
        ActivityLoginAndRegisterBinding.inflate(
            layoutInflater
        )
    }

    override fun getModelClazz(): Class<LoginAndRegisterViewModel> {
        return LoginAndRegisterViewModel::class.java
    }

    fun onBack(view: View) {
        onBackPressed()
    }

    var flag = 0
    fun onTheme(view: View) {
        lifecycleScope.launch {
            when (flag % 3) {
                0 -> {
                    setPreferenceTheme(PreferenceTheme.SIMPLE_BLUE)
                }
                1 -> {
                    setPreferenceTheme(PreferenceTheme.SIMPLE_NIGHT)
                }
                else -> {
                    setPreferenceTheme(PreferenceTheme.SIMPLE_DEFAULT)
                }
            }
        }

        flag++
    }
}