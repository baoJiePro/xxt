package com.baojie.kotlinproject.api

import com.baojie.kotlinproject.entity.LoginRegisterResponse
import com.baojie.kotlinproject.entity.LoginRegisterResponseWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/28 9:28 下午
 */
interface WanAndroidAPI {

    /**
     * 登录API
     * username=Derry-vip&password=123456
     */
    @POST("/user/login")
    @FormUrlEncoded
    fun loginAction(@Field("username") username: String,
                    @Field("password") password: String)
            : Observable<LoginRegisterResponseWrapper<LoginRegisterResponse>> // 返回值

    /**
     * 注册的API
     */
    @POST("/user/register")
    @FormUrlEncoded
    fun registerAction(@Field("username") username: String,
                       @Field("password") password: String,
                       @Field("repassword") repassword: String)
            : Observable<LoginRegisterResponseWrapper<LoginRegisterResponse>> // 返回值
}