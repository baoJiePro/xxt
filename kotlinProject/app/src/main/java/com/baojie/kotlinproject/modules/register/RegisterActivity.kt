package com.baojie.kotlinproject.modules.register

import android.os.Bundle
import android.widget.Toast
import com.baojie.kotlinproject.R
import com.baojie.kotlinproject.base.BaseActivity
import com.baojie.kotlinproject.entity.LoginRegisterResponse
import com.baojie.kotlinproject.modules.register.inner.RegisterPersenter
import com.baojie.kotlinproject.modules.register.inner.RegisterView
import kotlinx.android.synthetic.main.activity_user_register.*

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 1:50 下午
 */
class RegisterActivity: BaseActivity<RegisterPersenter>(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideActionBar()

        setContentView(R.layout.activity_user_register)

        user_register_bt.setOnClickListener {
            //调用注册
            val usernameStr = user_phone_et.text.toString()
            val passwordStr = user_password_et.text.toString()
            presenter.registerWanAndroid(this@RegisterActivity, usernameStr, passwordStr, passwordStr)
        }
    }

    override fun createP(): RegisterPersenter = RegisterPresenterImpl(this)

    override fun recycle() {
        presenter.unAttachView()
    }

    override fun registerSuccess(registerBean: LoginRegisterResponse?) {
        Toast.makeText(this, "注册成功😀", Toast.LENGTH_SHORT).show()
    }

    override fun registerFailed(errorMsg: String?) {
        Toast.makeText(this, "注册失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
    }
}