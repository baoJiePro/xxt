package com.baojie.kotlinproject.modules.collect.inter

import com.baojie.kotlinproject.database.Student

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 8:42 下午
 */
interface CollectModel {
    // ← 请求去
    fun requestInsert(listener: CollectPresenter.OnCollectListener, vararg students: Student)
    fun requestUpdate(listener: CollectPresenter.OnCollectListener, vararg students: Student)
    fun requestDelete(listener: CollectPresenter.OnCollectListener, vararg students: Student)
    fun requestDeleteAll(listener: CollectPresenter.OnCollectListener) // 删除全部

    fun requestQueryAll(listener: CollectPresenter.OnCollectResponseListener) // 查询全部

}