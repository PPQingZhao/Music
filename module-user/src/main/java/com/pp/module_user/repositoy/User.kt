package com.pp.module_user.repositoy

import com.pp.library_database.user.User
import com.pp.library_network.bean.user.UserInfoBean

fun User.setInfo(info: UserInfoBean?) {
    name = info?.login?.username
    e_mail = info?.login?.e_mail
    mobile = info?.login?.mobile
    nickName = info?.user?.nick
    motto = info?.user?.motto
    avatar = info?.user?.avatar
}