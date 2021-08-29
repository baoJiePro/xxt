package com.baojie.kotlinproject.modules.register.inner

import com.baojie.kotlinproject.entity.LoginRegisterResponse

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 1:43 下午
 */
interface RegisterView {
    // 成功 失败 显示 到 Activity

    fun registerSuccess(registerBean: LoginRegisterResponse?)

    fun registerFailed(errorMsg: String?)
}