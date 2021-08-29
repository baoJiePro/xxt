package com.baojie.kotlinproject.entity

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/28 9:31 下午
 */
data class LoginRegisterResponse(
    val admin: Boolean,
    val chapterTops: List<*>,
    val collectIds: List<*>,
    val email: String ?,
    val icon: String?,
    val id: String?,
    val nickname: String?,
    val password: String?,
    val publicName: String?,
    val token: String?,
    val type: Int,
    val username: String?
)
