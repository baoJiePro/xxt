package com.baojie.kotlinproject

import android.app.Application
import com.baojie.kotlinproject.database.StudentDatabase

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 11:38 下午
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        StudentDatabase.getDatabase(this)
    }
}