package com.baojie.kotlinproject.modules.collect.inter

import com.baojie.kotlinproject.database.Student

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 8:38 下午
 */
interface CollectView {
    // 显示数据  --- 》 View

    fun showResultSuccess(result: List<Student> ?)

    fun showResult(result: Boolean)
}