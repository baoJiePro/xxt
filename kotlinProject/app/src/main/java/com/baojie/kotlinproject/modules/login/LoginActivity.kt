package com.baojie.kotlinproject.modules.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.baojie.kotlinproject.R
import com.baojie.kotlinproject.api.WanAndroidAPI
import com.baojie.kotlinproject.config.Flag
import com.baojie.kotlinproject.entity.LoginRegisterResponse
import com.baojie.kotlinproject.modules.register.RegisterActivity
import com.baojie.kotlinproject.net.APIClient
import com.baojie.kotlinproject.net.APIResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        user_login_bt.setOnClickListener(onClickListener)

        user_register_tv.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private val onClickListener = View.OnClickListener { view ->
        when(view.id){
            // 登录
            R.id.user_login_bt -> {
                val userNameStr = user_phone_et.text.toString()
                val userPwdStr = user_password_et.text.toString()
                Log.d(Flag.TAG, "userName:$userNameStr,  userPwd:$userPwdStr")

                APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                    .loginAction(userNameStr, userPwdStr) // 起点  往下流  ”包装Bean“
                    .subscribeOn(Schedulers.io())// 给上面请求服务器的操作，分配异步线程
                    .observeOn(AndroidSchedulers.mainThread())// 给下面更新UI操作，分配main线程
                    .subscribe(object : APIResponse<LoginRegisterResponse>(this){
                        override fun success(data: LoginRegisterResponse?) {
                            // 成功  data UI
                            Log.e(Flag.TAG, "success: $data")
                            Toast.makeText(this@LoginActivity, "登录成功😀", Toast.LENGTH_SHORT).show()
                        }

                        override fun failure(errorMsg: String?) {
                            // 失败 msg UI
                            Log.e(Flag.TAG, "failure: errorMsg:$errorMsg")
                            Toast.makeText(this@LoginActivity, "登录失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
                        }

                    })

            }
        }
    }
}