package com.pp.module_local

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import com.pp.library_base.base.ThemeViewModel

class LocalViewModel(app: Application) : ThemeViewModel(app) {
    val headIcon = ObservableField<String>()
    val nickName = ObservableField<String>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

    }
}