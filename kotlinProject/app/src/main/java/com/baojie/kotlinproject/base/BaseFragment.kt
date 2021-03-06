package com.baojie.kotlinproject.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 8:46 下午
 */
abstract class BaseFragment<P>: Fragment() where P: IBasePresenter{
    lateinit var p: P
    private lateinit var mActivity: AppCompatActivity  // Fragment需要拿到 Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Java
        // mActivity = (AppCompatActivity) context

        // KT
        mActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        p = getPresenter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createOK()
    }

    abstract fun getPresenter() : P

    override fun onDestroy() {
        super.onDestroy()
        recycle()
    }

    abstract fun createOK()
    abstract fun recycle()

    /**
     * 暴露给自己的孩子 隐藏ActionBar
     */
    fun hideActionBar() : Unit {
        val actionBar: ActionBar? = mActivity?.supportActionBar
        actionBar?.hide();
    }

    /**
     * 暴露给自己的孩子 显示ActionBar
     */
    fun showActionBar() : Unit {
        mActivity?.supportActionBar?.show()
    }
}