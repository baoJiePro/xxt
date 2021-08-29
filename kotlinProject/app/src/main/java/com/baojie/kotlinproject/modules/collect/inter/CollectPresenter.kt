package com.baojie.kotlinproject.modules.collect.inter

import com.baojie.kotlinproject.base.IBasePresenter
import com.baojie.kotlinproject.database.Student

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 8:41 下午
 */
interface CollectPresenter: IBasePresenter {
    fun requestInsert(vararg students: Student)
    fun requestUpdate(vararg students: Student)
    fun requestDelete(vararg students: Student)
    fun requestDeleteAll() // 删除全部
    fun requestQueryAll() // 查询全部

    // → 回来处
    interface OnCollectResponseListener {
        fun showResultSuccess(result: List<Student> ?)
    }
    // → 回来处
    interface OnCollectListener {
        fun showIUD(iudResult: Boolean)
    }
}