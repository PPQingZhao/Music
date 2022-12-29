package com.pp.module_user.repositoy


import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.pp.library_base.datastore.userDataStore
import com.pp.library_common.app.App
import com.pp.library_database.AppDataBase
import com.pp.library_database.user.User
import com.pp.library_network.api.user.MusicService
import com.pp.library_network.bean.ResponseBean
import com.pp.library_network.bean.user.LoginBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

object UserRepository {
    private const val TAG = "UserRepository"
    private val userNameKey = stringPreferencesKey("user_name")
    private val passwordKey = stringPreferencesKey("password")

    private val userApi by lazy { MusicService.userApi }
    private val userDao by lazy {
        AppDataBase.instance.getUserDao()
    }

    /**
     * 登录preference 缓存中的user
     */
    suspend fun loginPreferenceUser(): Pair<ResponseBean<LoginBean>, User?> {
        var userName: String?
        var password: String?
        App.getInstance().baseContext.userDataStore.data.first().run {
            userName = get(userNameKey)
            password = get(passwordKey)

        }

        val loginPair = login(userName, password)
        if (userName?.isNotEmpty() == true) {
            return Pair(loginPair.first, User(name = userName, password = password))
        }
        return loginPair
    }

    /**
     * 成功登录后缓存到 preference
     */
    suspend fun loginWithPreferenceCache(
        userName: String?,
        password: String?,
    ): Pair<ResponseBean<LoginBean>, User?> {
        val result = login(userName, password)
        result.second?.apply {
            App.getInstance().baseContext.userDataStore.edit {
                it[userNameKey] = this.name.toString()
                it[passwordKey] = this.password.toString()
            }
        }
        return result
    }

    /**
     * 用户名&密码 登录
     */
    suspend fun login(userName: String?, password: String?): Pair<ResponseBean<LoginBean>, User?> {

        Log.v(TAG, "start login: $userName}")
        // 执行登录逻辑
        val loginResponse = userApi.loginByUserName(userName, password)

        Log.v(TAG, "login code: ${loginResponse.code}")
        if (loginResponse.code == MusicService.ErrorCode.SUCCESS) {
            val user = User(name = userName, password = password)
            val loginBean = loginResponse.data
            loginBean?.apply {
                user.token = token
                // head添加登录token
                MusicService.setToken(token)
            }

            // 获取uer info
            val userInfoResponse = MusicService.userApi.getUserInfo()

            if (userInfoResponse.code == MusicService.ErrorCode.SUCCESS) {
                userInfoResponse.data?.apply {
                    user.setInfo(this)
                }
            }

            // 插入数据库
            userDao.addUser(user)
            return Pair(loginResponse, user)
        }

        return Pair(loginResponse, null)
    }

    /**
     * 用户登出,清除preference缓存的用户信息
     */
    suspend fun logoutWithPreferenceClear() {
        withContext(Dispatchers.IO) {
            App.getInstance().baseContext.userDataStore.edit {
                it[userNameKey] = ""
                it[passwordKey] = ""
            }
        }
    }

    suspend fun register(username: String?, password: String?): ResponseBean<LoginBean> {
        return userApi.registerByUserName(username, password)
    }

}