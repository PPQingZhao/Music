package com.pp.library_network.api

import com.pp.library_network.bean.MusicBean
import com.pp.library_network.bean.ResponseBean
import retrofit2.http.Field
import retrofit2.http.GET

interface MusicApi {
    @GET("http://chitchat.doujunyu.vip/MusicByList")
    fun musicList(
        @Field("page") page: Int,
        @Field("page_size") page_size: Int,
        @Field("title") title: String? = "",
    ): ResponseBean<List<MusicBean>>
}