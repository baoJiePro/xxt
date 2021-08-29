package com.baojie.kotlinproject.modules.register

import android.content.Context
import com.baojie.kotlinproject.api.WanAndroidAPI
import com.baojie.kotlinproject.entity.LoginRegisterResponse
import com.baojie.kotlinproject.modules.register.inner.RegisterModule
import com.baojie.kotlinproject.modules.register.inner.RegisterPersenter
import com.baojie.kotlinproject.net.APIClient
import com.baojie.kotlinproject.net.APIResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 1:47 下午
 */
class RegisterModuleImpl: RegisterModule {
    override fun cancelRequest() {

    }

    override fun register(
        context: Context,
        username: String,
        password: String,
        repassword: String,
        callback: RegisterPersenter.OnRegisterListener
    ) {
        // 网络模型
        APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
            .registerAction(username, password, repassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : APIResponse<LoginRegisterResponse>(context) {
                override fun success(data: LoginRegisterResponse?) {
                    callback.registerSuccess(data)
                }

                override fun failure(errorMsg: String?) {
                    callback.registerFailed(errorMsg)
                }

            })
    }
}