package com.baojie.kotlinproject.modules.register

import android.content.Context
import com.baojie.kotlinproject.entity.LoginRegisterResponse
import com.baojie.kotlinproject.modules.register.inner.RegisterPersenter
import com.baojie.kotlinproject.modules.register.inner.RegisterView

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 1:49 下午
 */
class RegisterPresenterImpl constructor(var view: RegisterView?) : RegisterPersenter
    , RegisterPersenter.OnRegisterListener{
    // 需要  M 去请求 拿业务数据         搞定
    private val model = RegisterModuleImpl()
    override fun registerWanAndroid(
        context: Context,
        username: String,
        password: String,
        repassword: String
    ) {
        model.register(context, username, password, repassword, this)
    }

    override fun unAttachView() {
        view = null
        model.cancelRequest()
    }

    override fun registerSuccess(registerBean: LoginRegisterResponse?) {
        view?.registerSuccess(registerBean)
    }

    override fun registerFailed(errorMsg: String?) {
        view?.registerFailed(errorMsg)
    }
}