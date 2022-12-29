package com.pp.library_common.routerservice

import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_router_service.services.IUserService
import com.pp.library_router_service.services.RouterServiceImpl

fun userService() =
    ARouter.getInstance().build(RouterServiceImpl.User.SERVICE_USER).navigation() as IUserService
