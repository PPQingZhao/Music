package com.pp.module_user.ui

import android.app.Application
import androidx.databinding.ObservableField
import com.pp.library_base.base.ThemeViewModel
import com.pp.module_user.R

class LoginAndRegisterViewModel(application: Application) : ThemeViewModel(application) {
    val title = ObservableField<Int>(R.string.user_login)
}