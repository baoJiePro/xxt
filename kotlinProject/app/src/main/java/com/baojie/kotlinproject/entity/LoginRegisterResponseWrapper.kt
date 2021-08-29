package com.baojie.kotlinproject.entity

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/28 9:30 下午
 */
data class LoginRegisterResponseWrapper<T>(val data: T, val errorCode: Int, val errorMsg: String = "")
