package com.pp.module_user.model

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData

open class RegisterViewModel {
    val enable = ObservableBoolean(false)
    val errorMessage = MutableLiveData<String>("")
    val username = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val succeed = MutableLiveData<Boolean>(false)

    open fun onClick(view: View) {}

    open fun onReturn(view: View) {}
}