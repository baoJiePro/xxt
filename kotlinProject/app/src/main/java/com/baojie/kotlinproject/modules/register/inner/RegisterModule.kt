package com.baojie.kotlinproject.modules.register.inner

import android.content.Context

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 1:46 下午
 */
interface RegisterModule {

    fun cancelRequest()

    fun register(
        context: Context,
        username: String,
        password: String,
        repassword: String,

        // 把结果 给 P层  接口回调
        callback: RegisterPersenter.OnRegisterListener
    )
}