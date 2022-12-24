package com.pp.library_base.base

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.pp.library_base.datastore.PreferenceTheme
import com.pp.library_base.datastore.getPreferenceTheme
import com.pp.library_ui.BR
import com.pp.library_ui.utils.AppTheme
import com.pp.library_ui.utils.Theme
import com.pp.library_ui.utils.ViewTreeAppThemeViewModel
import com.pp.mvvm.LifecycleActivity
import kotlinx.coroutines.launch

/**
 * theme fragment
 */
abstract class ThemeActivity<VB : ViewDataBinding, VM : ThemeViewModel> :
    LifecycleActivity<VB, VM>() {

    val mThemeViewModel = AppTheme()
    private val mViewThemes by lazy { mutableListOf<Theme>() }

    private fun applyTheme(lifecycle: Lifecycle, theme: Theme) {
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                mViewThemes.remove(theme)
            }
        })
        theme.setTheme(getTheme())
        mViewThemes.add(theme)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        applyTheme(lifecycle, mThemeViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            getPreferenceTheme {
                when (it) {
                    PreferenceTheme.SIMPLE_NIGHT.ordinal -> {
                        setTheme(com.pp.library_ui.R.style.Theme_Night)
                    }
                    PreferenceTheme.SIMPLE_BLUE.ordinal -> {
                        setTheme(com.pp.library_ui.R.style.Theme_Blue)
                    }
                    else -> {
                        setTheme(com.pp.library_ui.R.style.AppTheme)
                    }
                }
            }
        }

        // windowBackground 主题变化
        mThemeViewModel.windowBackground.observe(this) {
            window.setBackgroundDrawable(it)
        }

        ViewTreeAppThemeViewModel.set(mBinding.root, mThemeViewModel)
    }

    @CallSuper
    override fun onSetVariable(binding: VB, viewModel: VM): Boolean {
        // set theme variable
        mBinding.setVariable(BR.themeViewModel, mThemeViewModel)
        return super.onSetVariable(binding, viewModel)
    }

    override fun onApplyThemeResource(theme: Resources.Theme?, resid: Int, first: Boolean) {
        super.onApplyThemeResource(theme, resid, first)

//        Log.e("TAG", "onApplyThemeResource")
        theme?.apply {
            mViewThemes.forEach {
                it.setTheme(theme)
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setTranslucent()
    }

    /**
     * 设置状态栏字体颜色
     * TODO 未做设配
     */
    fun requireLightStatusBar(light: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                if (light) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                if (light) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else View.VISIBLE
        }

    }

    private fun setTranslucent() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}