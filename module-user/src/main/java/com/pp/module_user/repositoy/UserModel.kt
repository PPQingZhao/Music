package com.pp.module_user.repositoy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pp.library_database.user.User
import com.pp.library_network.api.user.MusicService
import com.pp.library_network.bean.ResponseBean
import com.pp.library_network.bean.user.LoginBean
import com.pp.library_network.bean.user.UserInfoBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class UserModel(private val user: User) {

    private val isLogin = MutableLiveData<Boolean>()
    private val loginBean = MutableLiveData<LoginBean>()
    private val userInfoBean = MutableLiveData<UserInfoBean>()

    companion object {
        private val TAG = "UserModel"
    }

    fun userInfo(): LiveData<UserInfoBean> {
        return userInfoBean
    }

    fun isLogin(): LiveData<Boolean> {
        return isLogin
    }

    fun getName(): String? {
        return user.name
    }

    fun getPassword(): String? {
        return user.password
    }

    suspend fun login(): ResponseBean<LoginBean> {
        Log.v(TAG, "start login: ${user.name}")
        // 执行登录逻辑
        val loginResponse = MusicService.userApi.loginByUserName(user.name, user.password)

        Log.v(TAG, "login code: ${loginResponse.code}")
        if (loginResponse.code == MusicService.ErrorCode.SUCCESS) {
            withContext(Dispatchers.Main) {
                loginBean.value = loginResponse.data
                MusicService.setToken(loginResponse.data.token)
            }

            val userInfoResponse = MusicService.userApi.getUserInfo()
            withContext(Dispatchers.Main) {
                if (userInfoResponse.code == MusicService.ErrorCode.SUCCESS) {
                    val infoBean = userInfoResponse.data
                    userInfoBean.value = infoBean
                    isLogin.value = infoBean.login.is_login == MusicService.LoginStatus.LOGIN
                } else {
                    userInfoBean.value = null
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                reset()
            }
        }
        return loginResponse
    }

    private fun reset() {
        MusicService.setToken("")
        isLogin.value = false
        loginBean.value = null
    }

    suspend fun logout(): ResponseBean<String> {
        Log.v(TAG, "start logout: ${user.name}")
        withContext(Dispatchers.Main) {
            reset()
        }
        return ResponseBean(0, "", "")
    }

}