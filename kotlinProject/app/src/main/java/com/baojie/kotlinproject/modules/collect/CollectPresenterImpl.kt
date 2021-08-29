package com.baojie.kotlinproject.modules.collect

import com.baojie.kotlinproject.database.Student
import com.baojie.kotlinproject.modules.collect.inter.CollectPresenter
import com.baojie.kotlinproject.modules.collect.inter.CollectView

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 9:29 下午
 */
class CollectPresenterImpl(var view: CollectView?) : CollectPresenter,
    CollectPresenter.OnCollectListener, CollectPresenter.OnCollectResponseListener {

    private val module = CollectModuleImpl()

    override fun requestInsert(vararg students: Student) {
        module.requestInsert(this, *students)
    }

    override fun requestUpdate(vararg students: Student) {
        module.requestUpdate(this, *students)
    }

    override fun requestDelete(vararg students: Student) {
        module.requestDelete(this, *students)
    }

    override fun requestDeleteAll() {
        module.requestDeleteAll(this)
    }

    override fun requestQueryAll() {
        module.requestQueryAll(this)
    }

    override fun unAttachView() {
        view = null
    }

    override fun showResultSuccess(result: List<Student>?) {
        view?.showResultSuccess(result)
    }

    override fun showIUD(iudResult: Boolean) {
        view?.showResult(iudResult)
    }
}