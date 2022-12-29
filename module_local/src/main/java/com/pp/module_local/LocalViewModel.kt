package com.pp.module_local

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import com.pp.library_base.base.ThemeViewModel
import com.pp.library_common.routerservice.userService

class LocalViewModel(app: Application) : ThemeViewModel(app) {
    val headIcon = ObservableField<String>()
    val nickName = ObservableField<String>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        userService().apply {
            getHeadIcon().observe(owner) {
                headIcon.set(it)
            }

            getNickName().observe(owner) {
                nickName.set(it)
            }
        }
    }
}