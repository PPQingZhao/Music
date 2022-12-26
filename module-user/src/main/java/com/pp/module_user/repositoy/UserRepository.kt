package com.pp.module_user.repositoy


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
    private val userNameKey = stringPreferencesKey("user_name")
    private val passwordKey = stringPreferencesKey("password")
//    private val userDao by lazy {
//        AppDataBase.instance.getUserDao()
//    }

    suspend fun loginPreferenceUser(): Pair<UserModel, ResponseBean<LoginBean>> {
        var userName: String?
        var password: String?
        App.getInstance().baseContext.userDataStore.data.first().run {
            userName = get(userNameKey)
            password = get(passwordKey)

        }
        val loginPair = login(userName, password)
        if (loginPair.second.code == MusicService.ErrorCode.SUCCESS) {
            withContext(Dispatchers.IO) {
                App.getInstance().baseContext.userDataStore.edit {
                    it[userNameKey] = userName.toString()
                    it[passwordKey] = password.toString()
                }
            }
        }
        return loginPair
    }

    suspend fun login(
        userName: String?,
        password: String?,
    ): Pair<UserModel, ResponseBean<LoginBean>> {

        val user = User(name = userName, password = password)
        val userModel = UserModel(user)
        // 执行登录逻辑
        val response = userModel.login()
        /* // 登录成功,保存用户信息到数据库
         if (response.code == MusicService.ErrorCode.SUCCESS) {
             withContext(Dispatchers.IO) {
                 userDao.addUser(user)
             }
         }*/
        return Pair(userModel, response)
    }

    suspend fun logoutPreferenceUser(userModel: UserModel): ResponseBean<String> {
        withContext(Dispatchers.IO) {
            App.getInstance().baseContext.userDataStore.edit {
                it[userNameKey] = ""
                it[passwordKey] = ""
            }
        }
        return userModel.logout()
    }

    suspend fun register(username: String?, password: String?): ResponseBean<LoginBean> {
        return MusicService.userApi.registerByUserName(username, password)
    }

}