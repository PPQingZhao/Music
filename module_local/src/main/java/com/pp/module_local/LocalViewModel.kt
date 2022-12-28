package com.pp.module_local

import android.app.Application
import androidx.databinding.ObservableField
import com.pp.library_base.base.ThemeViewModel

class LocalViewModel(app: Application) : ThemeViewModel(app) {
    val headIcon = ObservableField<String>()
    val nickName = ObservableField<String>("用户1344425550")
}