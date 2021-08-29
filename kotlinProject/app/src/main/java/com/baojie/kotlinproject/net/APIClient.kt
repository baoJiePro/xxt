package com.baojie.kotlinproject.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/28 9:22 下午
 */
class APIClient {
    private object Holder{
        val INSTANCE = APIClient()
    }

    companion object{
        val instance = Holder.INSTANCE
    }

    fun <T> instanceRetrofit(apiInterface: Class<T>): T{
        val okHttpClient = OkHttpClient().newBuilder()
            // 添加读取超时时间
            .readTimeout(10000, TimeUnit.SECONDS)

            // 添加连接超时时间
            .connectTimeout(10000, TimeUnit.SECONDS)

            // 添加写出超时时间
            .writeTimeout(10000, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJava来处理
            .addConverterFactory(GsonConverterFactory.create()) // Gson 来解析 --- JavaBean
            .build()
        return retrofit.create(apiInterface)
    }
}