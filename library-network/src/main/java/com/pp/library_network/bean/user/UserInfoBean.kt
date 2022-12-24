package com.pp.library_network.bean.user

data class UserInfoBean(
    val is_admin: Int = 0,
    val login: Login = Login(),
    val `property`: Property = Property(),
    val user: User = User(),
) {
    data class Login(
        val e_mail: String = "",
        val is_login: Int = -1,
        val last_login_ip: String = "",
        val last_login_time: String = "",
        val mobile: String = "",
        val username: String = "",
    )

    data class Property(
        val id: Int = 0,
        val money: String = "",
        val user_id: Int = 0,
        val vip: Int = 0,
    )

    data class User(
        val avatar: String = "",
        val id: Int = 0,
        val motto: String = "",
        val nick: String = "",
    )
}