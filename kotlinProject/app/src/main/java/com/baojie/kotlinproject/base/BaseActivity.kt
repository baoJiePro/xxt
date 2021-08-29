package com.baojie.kotlinproject.base

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 1:19 下午
 */
//P类型限定
abstract class BaseActivity<P>: AppCompatActivity() where P: IBasePresenter, P:IBasePresenter2{

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createP()
    }

    abstract fun createP(): P

    override fun onDestroy() {
        super.onDestroy()
        recycle()
    }

    abstract fun recycle()

    // 类似于工具类函数
    /**
     * 暴漏给自己的孩子   隐藏ActionBar
     */
    fun hideActionBar() {
        // 任何 Java代码东西，必须用 ？ 允许为null，来接收
        supportActionBar?.hide()
    }

    /**
     * 暴漏给自己的孩子   显示ActionBar
     */
    fun showActionBar() {
        supportActionBar?.show()
    }
}