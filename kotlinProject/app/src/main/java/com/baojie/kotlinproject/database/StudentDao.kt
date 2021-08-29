package com.baojie.kotlinproject.database

import androidx.room.*

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 2:40 下午
 */
@Dao
interface StudentDao {

    @Insert
    fun insertStudents(vararg student: Student)

    @Update
    fun updateStudents(vararg student: Student)

    @Query("DELETE FROM student")
    fun deleteAllStudents()

    // 根据条件删除
    @Delete
    fun deleteStudents(vararg students: Student)

    // 查询全部
    @Query("SELECT * FROM student ORDER BY ID DESC")
    fun queryAllStudents() : List<Student>
}