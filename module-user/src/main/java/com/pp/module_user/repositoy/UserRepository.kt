package com.pp.module_user.repositoy


import android.util.Log
import com.pp.library_database.AppDataBase
import com.pp.library_database.user.User
import com.pp.library_network.api.user.MusicService
import com.pp.library_network.bean.ResponseBean
import com.pp.library_network.bean.user.LoginBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

object UserRepository {

    private val userDao by lazy {
        AppDataBase.instance.getUserDao()
    }

    fun login(
        userName: String?,
        password: String?,
    ): Flow<Pair<UserModel, ResponseBean<LoginBean>>> {

        val user = User(name = userName, password = password)
        val userModel = UserModel(user)
        // 执行登录逻辑
        return userModel.login()
            .onEach { response ->
                // 登录成功,保存用户信息到数据库
                if (response.code == MusicService.ErrorCode.SUCCESS) {
                    userDao.addUser(user)
                }
            }.map { response ->
                Pair(userModel, response)
            }.flowOn(Dispatchers.IO)
    }

    fun logout(userModel: UserModel): Flow<ResponseBean<String>> {
        return userModel.logout()
    }

    fun register(username: String?, password: String?): Flow<ResponseBean<LoginBean>> {
        return flow {
            val registerResponse = MusicService.userApi.registerByUserName(username, password)
            emit(registerResponse)
        }
    }

}