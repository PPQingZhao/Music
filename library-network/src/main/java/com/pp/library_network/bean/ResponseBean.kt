package com.pp.library_network.bean

data class ResponseBean<Data>(
    val code: Int,
    val `data`: Data,
    val msg: String,
)