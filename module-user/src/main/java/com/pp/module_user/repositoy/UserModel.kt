package com.pp.module_user.repositoy

import androidx.lifecycle.MutableLiveData
import com.pp.library_database.user.User

class UserModel {

    companion object {
        private const val TAG = "UserModel"
    }

    private val _userName = MutableLiveData<String?>()
    val userName = _userName

    private val _nickName = MutableLiveData<String?>()
    val nickName = _nickName

    private val _password = MutableLiveData<String?>()
    val password = _password

    private val _loginToken = MutableLiveData<String?>()
    val loginToken = _loginToken

    private val _headIcon = MutableLiveData<String?>()
    val headIcon = _headIcon

    private val _motto = MutableLiveData<String?>()
    val motto = _motto

    var user: User? = null
        set(value) {
            field = value
            _userName.value = value?.name
            _nickName.value = value?.nickName
            _password.value = value?.password
            _loginToken.value = value?.token
            _headIcon.value = value?.avatar
            motto.value = value?.motto
        }


}