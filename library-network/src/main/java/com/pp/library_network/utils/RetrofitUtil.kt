package com.pp.library_network.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    fun create(baseUrl: String): Retrofit {

        val builder = Retrofit.Builder()
            .client(HttpUtil.getClient())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }


}