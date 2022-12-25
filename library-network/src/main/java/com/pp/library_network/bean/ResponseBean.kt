package com.pp.library_network.bean

import com.google.gson.annotations.JsonAdapter
import com.pp.library_network.utils.ObjectTypeAdapter

data class ResponseBean<Data>(
    val code: Int,
    @JsonAdapter(ObjectTypeAdapter::class)
    val `data`: Data,
    val msg: String,
)