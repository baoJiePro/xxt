package com.baojie.kotlinproject.modules.collect

import com.baojie.kotlinproject.data_model.local.LocalRoomRequestManager
import com.baojie.kotlinproject.database.Student
import com.baojie.kotlinproject.modules.collect.inter.CollectModel
import com.baojie.kotlinproject.modules.collect.inter.CollectPresenter

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 11:06 下午
 */
class CollectModuleImpl: CollectModel {
    override fun requestInsert(
        listener: CollectPresenter.OnCollectListener,
        vararg students: Student
    ) {
        LocalRoomRequestManager.getInstance().insertStudents(*students)
        listener.showIUD(true)
    }

    override fun requestUpdate(
        listener: CollectPresenter.OnCollectListener,
        vararg students: Student
    ) {
        LocalRoomRequestManager.getInstance().updateStudents(*students)
        listener.showIUD(true)
    }

    override fun requestDelete(
        listener: CollectPresenter.OnCollectListener,
        vararg students: Student
    ) {
        LocalRoomRequestManager.getInstance().deleteStudents(*students)
        listener.showIUD(true)
    }

    override fun requestDeleteAll(listener: CollectPresenter.OnCollectListener) {
        LocalRoomRequestManager.getInstance().deleteAllStudent()
        listener.showIUD(true)
    }

    override fun requestQueryAll(listener: CollectPresenter.OnCollectResponseListener) {
        val result = LocalRoomRequestManager.getInstance().queryAllStudent()
        listener.showResultSuccess(result)
    }
}