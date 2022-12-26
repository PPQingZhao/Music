package com.pp.module_user.model

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

open class RegisterViewModel {
    val enable = ObservableBoolean(false)
    val errorMessage = MutableLiveData<String>("")
    val helperMessage = MutableLiveData<String>("")
    val username = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val succeed = MutableLiveData<Boolean>()

    open fun onClick(view: View) {}

    open fun onReturn(view: View) {}
}