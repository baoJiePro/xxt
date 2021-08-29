package com.baojie.kotlinproject.data_model.local

import com.baojie.kotlinproject.database.Student
import com.baojie.kotlinproject.database.StudentDao
import com.baojie.kotlinproject.database.StudentDatabase

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 11:12 下午
 */
class LocalRoomRequestManager: IDatabaseRequest {

    var studentDao: StudentDao? = null

    init {
        val studentDatabase = StudentDatabase.getDatabase()
        studentDao = studentDatabase?.getStudentDao()
    }

    companion object{
        var INSTANCE: LocalRoomRequestManager? = null
        fun getInstance(): LocalRoomRequestManager{
            if (INSTANCE == null){
                synchronized(LocalRoomRequestManager::class){
                    if (INSTANCE == null){
                        INSTANCE = LocalRoomRequestManager()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    override fun insertStudents(vararg students: Student) {
        studentDao?.insertStudents(*students)
    }

    override fun updateStudents(vararg students: Student) {
        studentDao?.updateStudents(*students)
    }

    override fun deleteStudents(vararg students: Student) {
        studentDao?.deleteStudents(*students)
    }

    override fun deleteAllStudent() {
        studentDao?.deleteAllStudents()
    }

    override fun queryAllStudent(): List<Student>? {
        return studentDao?.queryAllStudents()
    }
}