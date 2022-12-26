package com.pp.module_user.manager

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pp.library_network.api.user.MusicService
import com.pp.library_network.bean.ResponseBean
import com.pp.library_network.bean.user.LoginBean
import com.pp.module_user.repositoy.UserModel
import com.pp.module_user.repositoy.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

object UserManager {

    private val userModel = MutableLiveData<UserModel?>()

    private var hasUser = false


    fun userModel(): LiveData<UserModel?> {
        return userModel
    }

    suspend fun loginExistUser() {
        logout()
        val result = UserRepository.loginPreferenceUser()
        withContext(Dispatchers.Main) {
            processLoginResult(result)
        }
    }

    suspend fun login(
        userName: String?,
        password: String?,
    ): ResponseBean<LoginBean> {
        // 先登出
        logout()
        // 登录用户
        val loginPair = UserRepository.login(userName, password)
        withContext(Dispatchers.Main) {
            processLoginResult(loginPair)
        }
        return loginPair.second
    }

    @UiThread
    private fun processLoginResult(loginPair: Pair<UserModel, ResponseBean<LoginBean>>) {
        if (loginPair.second.code == MusicService.ErrorCode.SUCCESS) {
            val model = loginPair.first
            userModel.value = model
            hasUser = true
        }
    }

    suspend fun logout(): ResponseBean<String> {
        withContext(Dispatchers.Main) {
            userModel.value = null
            hasUser = false
        }
        return if (userModel.value == null) {
            return ResponseBean(0, "", "")
        } else {
            UserRepository.logoutPreferenceUser(userModel.value!!)
        }
    }

    fun hasUser(): Boolean {
        return hasUser
    }
}