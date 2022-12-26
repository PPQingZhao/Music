package com.pp.module_user.model

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.pp.library_network.api.user.MusicService
import com.pp.module_user.manager.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserLoginViewModel : LoginViewModel(), DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        username.value = UserManager.userModel().value?.getName()
        password.value = UserManager.userModel().value?.getPassword()

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

    private val _loginResult = MutableSharedFlow<Boolean>()
    val loginResult: SharedFlow<Boolean> = _loginResult

    override fun onClick(view: View) {
        succeed.value = false
        errorMessage.value = ""
        helperMessage.value = ""
        ViewTreeLifecycleOwner.get(view)?.lifecycleScope?.launch {

            try {
                val response = UserManager.login(username.value, password.value)
                val result = response.code == MusicService.ErrorCode.SUCCESS

                _loginResult.emit(result)
                withContext(Dispatchers.Main) {
                    helperMessage.value = response.msg
                    succeed.value = result
                }
            } catch (e: Throwable) {
                Log.e("UserLoginViewModel", "${e.message}")
                errorMessage.value = "发生错误"
            }
        }
    }

    private val _OnNewUser = MutableSharedFlow<Boolean>()
    val onNewUser: SharedFlow<Boolean> = _OnNewUser
    override fun onNewUser(view: View) {
        ViewTreeLifecycleOwner.get(view)?.lifecycleScope?.launch {
            _OnNewUser.emit(true)
        }
    }
}