package com.baojie.kotlinproject.net

import android.content.Context
import com.baojie.kotlinproject.entity.LoginRegisterResponseWrapper
import com.xiangxue.kotlinproject.LoadingDialog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/28 10:05 下午
 */
abstract class APIResponse<T>(private val context: Context): Observer<LoginRegisterResponseWrapper<T>> {

    private var isShow: Boolean = true

    // 次构造
    constructor(context: Context, isShow: Boolean = false) : this(context) {
        this.isShow = isShow
    }

    // 成功 怎么办
    abstract fun success(data: T ?)

    // 失败 怎么办
    abstract fun failure(errorMsg: String?)

    // 启点分发的时候
    override fun onSubscribe(d: Disposable) {
        // 弹出 加载框
        if (isShow) {
            LoadingDialog.show(context)
        }
    }

    // 上游流下了的数据
    override fun onNext(t: LoginRegisterResponseWrapper<T>) {

        if (t.data == null) {
            // 失败
            failure("登录失败了，请检查原因：msg:${t.errorMsg}")
        } else {
            // 成功
            success(t.data)
        }
    }

    // 上游流下了的错误
    override fun onError(e: Throwable) {
        // 取消加载
        LoadingDialog.cancel()

        failure(e.message)
    }

    // 停止
    override fun onComplete() {
        // 取消加载
        LoadingDialog.cancel()
    }
}