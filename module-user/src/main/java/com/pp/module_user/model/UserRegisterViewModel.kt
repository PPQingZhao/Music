package com.pp.module_user.model

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.pp.library_network.api.user.MusicService
import com.pp.module_user.manager.UserManager
import com.pp.module_user.repositoy.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRegisterViewModel : RegisterViewModel(), DefaultLifecycleObserver {

    private var lifecycleScope: LifecycleCoroutineScope? = null
    override fun onCreate(owner: LifecycleOwner) {
        lifecycleScope = owner.lifecycleScope

        val observer = { v: String? ->
            errorMessage.value = ""
            helperMessage.value = ""
            enable.set(
                !(username.value?.isEmpty() ?: true)
                        && !(password.value?.isEmpty() ?: true)
            )
        }
        username.observe(owner, observer)
        password.observe(owner, observer)
    }

    fun isRegisterSucceed(): LiveData<Boolean> {
        return succeed
    }

    override fun onClick(view: View) {
        errorMessage.value = ""
        helperMessage.value = ""
        succeed.value = false

        lifecycleScope?.launch {
            UserRepository.register(username.value, password.value)
                .catch {
                    Log.e("UserRegisterViewModel","${it.message}")
                    errorMessage.value = "发生错误"
                }
                .collect {
                    withContext(Dispatchers.Main) {
                        helperMessage.value = it.msg
                        succeed.value = it.code == MusicService.ErrorCode.SUCCESS
                    }
                }
        }
    }

    var onReturn: (() -> Unit)? = null
    override fun onReturn(view: View) {
        onReturn?.invoke()
    }

}