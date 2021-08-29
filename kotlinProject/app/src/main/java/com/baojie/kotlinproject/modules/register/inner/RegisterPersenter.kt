package com.baojie.kotlinproject.modules.register.inner

import android.content.Context
import com.baojie.kotlinproject.base.IBasePresenter
import com.baojie.kotlinproject.base.IBasePresenter2
import com.baojie.kotlinproject.entity.LoginRegisterResponse

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 1:44 下午
 */
interface RegisterPersenter: IBasePresenter, IBasePresenter2 {

    fun registerWanAndroid(context: Context, username: String, password: String, repassword: String)

    // M  ---》 P  接口监听
    interface OnRegisterListener {

        fun registerSuccess(registerBean: LoginRegisterResponse?)

        fun registerFailed(errorMsg: String?)

    }
}