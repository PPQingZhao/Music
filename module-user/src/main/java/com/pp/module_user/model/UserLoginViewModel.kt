package com.pp.module_user.model

import android.view.View
import androidx.lifecycle.*
import com.pp.library_network.api.user.MusicService
import com.pp.module_user.manager.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserLoginViewModel : LoginViewModel(), DefaultLifecycleObserver {

    private var lifecycleScope: LifecycleCoroutineScope? = null
    override fun onCreate(owner: LifecycleOwner) {
        lifecycleScope = owner.lifecycleScope

        username.value = UserManager.userModel().value?.getName()
        password.value = UserManager.userModel().value?.getPassword()

        val observer = { v: String? ->
            errorMessage.value = ""
            enable.set(
                !(username.value?.isEmpty() ?: true)
                        && !(password.value?.isEmpty() ?: true)
            )
        }
        username.observe(owner, observer)
        password.observe(owner, observer)

    }

    fun isLogin(): LiveData<Boolean> {
        return succeed
    }

    override fun onClick(view: View) {
        succeed.value = false
        errorMessage.value = ""
        lifecycleScope?.launch {
            UserManager.login(username.value, password.value)
                .catch {
                    it.printStackTrace()
                }
                .collect {
                    withContext(Dispatchers.Main) {
                        errorMessage.value = it.msg
                        succeed.value = it.code == MusicService.ErrorCode.SUCCESS
                    }
                }
        }
    }

    var onNewUser: (() -> Unit)? = null
    override fun onNewUser(view: View) {
        onNewUser?.invoke()
    }
}