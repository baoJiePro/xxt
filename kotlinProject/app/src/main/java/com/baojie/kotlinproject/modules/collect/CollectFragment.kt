package com.baojie.kotlinproject.modules.collect

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.baojie.kotlinproject.R
import com.baojie.kotlinproject.adapter.CollectAdapter
import com.baojie.kotlinproject.base.BaseFragment
import com.baojie.kotlinproject.database.Student
import com.baojie.kotlinproject.modules.collect.inter.CollectPresenter
import com.baojie.kotlinproject.modules.collect.inter.CollectView
import kotlinx.android.synthetic.main.fragment_collect.*

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 8:18 下午
 */
class CollectFragment: BaseFragment<CollectPresenter>(), CollectView {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(activity, "收藏", Toast.LENGTH_SHORT).show()
        val root: View? = inflater.inflate(R.layout.fragment_collect, container, false)
        return root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    init {
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addData.setOnClickListener {
            val names = arrayOf<String> (
                "乔峰",
                "段誉",
                "虚竹",
                "慕容复",
                "张三",
                "李四",
                "王五",
                "赵六",
                "初七",
                "杜子腾",
                "王小二",
                "李大奇"
            )
            val ages = arrayOf<Int> (
                43,
                24,
                19,
                83,
                64,
                21,
                56,
                32,
                17,
                32,
                45,
                14
            )
            for (index in names.indices) {
                val stu = Student()
                stu.name = names[index]
                stu.age = ages[index]
                p.requestInsert(stu)
            }
        }

        clearData.setOnClickListener {
            p.requestDeleteAll()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.personal_menu, menu)
    }

    override fun getPresenter(): CollectPresenter {
        return CollectPresenterImpl(this)
    }

    override fun createOK() {
        p.requestQueryAll()
    }

    override fun recycle() {
        p.unAttachView()
    }

    override fun showResultSuccess(result: List<Student>?) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = CollectAdapter()
        if (result != null){
            adapter.allStudents = result
        }
        recyclerView.adapter = adapter
    }

    override fun showResult(result: Boolean) {
        if (result) p.requestQueryAll()
    }
}