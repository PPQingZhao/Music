package com.pp.module_local.model

import android.content.res.Resources.Theme
import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.pp.library_base.datastore.PreferenceTheme
import com.pp.library_base.datastore.getPreferenceTheme
import com.pp.library_base.datastore.setPreferenceTheme
import com.pp.library_common.app.App
import com.pp.library_ui.R
import kotlinx.coroutines.launch

class ItemPreferenceThemeSettingViewModel : ItemThemeSettingViewModel, DefaultLifecycleObserver {

    private val preferenceTheme: PreferenceTheme

    constructor(resTheme: Theme, preferenceTheme: PreferenceTheme) : super() {
        this.preferenceTheme = preferenceTheme
        when (preferenceTheme) {
            PreferenceTheme.SIMPLE_NIGHT -> {
                resTheme.applyStyle(R.style.Theme_Night, true)
            }
            PreferenceTheme.SIMPLE_BLUE -> {
                resTheme.applyStyle(R.style.Theme_Blue, true)
            }
            else -> {
                resTheme.applyStyle(R.style.AppTheme, true)
            }
        }

        appTheme.setTheme(resTheme)
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        owner.lifecycleScope.launch {
            App.getInstance().getPreferenceTheme {
                checked.set(preferenceTheme.ordinal == it)
            }
        }
    }

    override fun onItemClick(v: View) {
        if (checked.get()) {
            return
        }
        checked.set(true)
        ViewTreeLifecycleOwner.get(v)?.lifecycleScope?.launch {
            v.context.setPreferenceTheme(preferenceTheme)
        }
    }

}