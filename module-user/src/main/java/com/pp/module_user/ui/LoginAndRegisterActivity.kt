package com.pp.module_user.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.view.View
import android.view.animation.AlphaAnimation
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.base.ThemeActivity
import com.pp.library_base.datastore.PreferenceTheme
import com.pp.library_router_service.services.RouterPath
import com.pp.module_user.R
import com.pp.module_user.databinding.ActivityLoginAndRegisterBinding
import com.pp.module_user.databinding.ViewLoginBindingImpl
import com.pp.module_user.databinding.ViewRegisterBindingImpl

@Route(path = RouterPath.User.activity_login)
class LoginAndRegisterActivity :
    ThemeActivity<ActivityLoginAndRegisterBinding, LoginAndRegisterViewModel>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, LoginAndRegisterActivity::class.java)
            val toBundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            activity.startActivity(intent, toBundle)
        }
    }

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

    override fun onGetPreferenceTheme(theme: Int?): Boolean {
        when (theme) {
            PreferenceTheme.SIMPLE_NIGHT.ordinal -> {
                setTheme(R.style.Theme_Night_Login)
            }
            PreferenceTheme.SIMPLE_BLUE.ordinal -> {
                setTheme(R.style.Theme_Blue_Login)
            }
            else -> {
                setTheme(R.style.Theme_Default_Login)
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.enterTransition = Fade()
        window.exitTransition = Fade()
        initSwitchView()
    }

    private fun initSwitchView() {
        val viewLoginBinding = ViewLoginBindingImpl.inflate(
            layoutInflater,
            mBinding.viewSwitcher,
            false
        )
        mViewModel.loginViewModel.isLogin().observe(this) {
            if (it == true) {
                finishAfterTransition()
            }
        }
        mViewModel.loginViewModel.onNewUser = {
            mBinding.viewSwitcher.showPrevious()
        }
        viewLoginBinding.lifecycleOwner = this
        viewLoginBinding.themeViewModel = mThemeViewModel
        viewLoginBinding.viewModel = mViewModel.loginViewModel

        val viewRegisterBinding = ViewRegisterBindingImpl.inflate(
            layoutInflater,
            mBinding.viewSwitcher,
            false
        )
        mViewModel.registerViewModel.onReturn = {
            mBinding.viewSwitcher.showNext()
        }
        mViewModel.registerViewModel.isRegisterSucceed().observe(this) {
            if (it == true) {
                mViewModel.loginViewModel.username.value =
                    mViewModel.registerViewModel.username.value
                mViewModel.loginViewModel.password.value =
                    mViewModel.registerViewModel.password.value
                mBinding.viewSwitcher.showNext()
            }
        }
        viewRegisterBinding.lifecycleOwner = this
        viewRegisterBinding.themeViewModel = mThemeViewModel
        viewRegisterBinding.viewModel = mViewModel.registerViewModel

        mBinding.viewSwitcher.setFactory {
            if (viewRegisterBinding.root.parent == null) {
                viewRegisterBinding.root
            } else {
                viewLoginBinding.root
            }
        }

        mBinding.viewSwitcher.inAnimation = inAlphaAnimation()
        mBinding.viewSwitcher.outAnimation = outAlphaAnimation()
        mBinding.viewSwitcher.showNext()
    }

    private fun inAlphaAnimation(): AlphaAnimation {
        val inAlphaAnimation = AlphaAnimation(0.3f, 1f)
        inAlphaAnimation.duration = 500
        return inAlphaAnimation
    }

    private fun outAlphaAnimation(): AlphaAnimation {
        val outAlphaAnimation = AlphaAnimation(0f, 0f)
        outAlphaAnimation.duration = 500
        return outAlphaAnimation
    }

}