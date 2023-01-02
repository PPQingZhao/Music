package com.pp.module_local.model

import android.view.View
import androidx.databinding.ObservableBoolean
import com.pp.library_ui.utils.AppTheme

open class ItemThemeSettingViewModel {
    val checked = ObservableBoolean(false)
    var appTheme: AppTheme = AppTheme()

    open fun onItemClick(v: View) {}
}