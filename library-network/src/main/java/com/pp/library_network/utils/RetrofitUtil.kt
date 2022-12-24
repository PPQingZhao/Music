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

    fun create(
        baseUrl: String,
        querys: Map<String, String>? = null,
        headers: Map<String, String>? = null,
    ): Retrofit {

        val builder = Retrofit.Builder()
            .client(HttpUtil.getClient(querys, headers))
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }


}