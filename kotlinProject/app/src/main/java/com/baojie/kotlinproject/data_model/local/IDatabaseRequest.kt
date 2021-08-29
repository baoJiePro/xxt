package com.baojie.kotlinproject.data_model.local

import com.baojie.kotlinproject.database.Student

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 11:11 下午
 */
interface IDatabaseRequest {
    fun insertStudents(vararg students: Student)

    fun updateStudents(vararg students: Student)

    fun deleteStudents(vararg students: Student)

    fun deleteAllStudent()

    fun queryAllStudent() : List<Student> ?
}