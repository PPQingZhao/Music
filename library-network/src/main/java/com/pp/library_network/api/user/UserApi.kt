package com.pp.library_network.api.user

interface UserApi {

    suspend fun loginNyUserName()

    suspend fun registerByUserName()

    suspend fun getUserInfo()

    suspend fun modifyUserInfo()

    suspend fun upLoadPicture()


}