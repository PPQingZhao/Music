package com.pp.module_user.routerservice

import android.content.Context
import androidx.lifecycle.LiveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_router_service.services.IUserService
import com.pp.library_router_service.services.RouterServiceImpl
import com.pp.module_user.manager.UserManager

@Route(path = RouterServiceImpl.User.SERVICE_USER)
class UserServiceImpl : IUserService {

    override fun getToken(): LiveData<String?> {
        return UserManager.getToken()
    }

    override fun getNickName(): LiveData<String?> {
        return UserManager.getNickName()
    }
    override fun getHeadIcon(): LiveData<String?> {
        return UserManager.getHeadIcon()
    }

    override fun getMotto(): LiveData<String?> {
        return UserManager.getMotto()
    }

    override fun init(context: Context?) {
    }
}