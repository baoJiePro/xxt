package com.baojie.learn.project_jni.day23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import com.baojie.learn.project_jni.R
import com.blankj.utilcode.util.LogUtils

class ParcelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcel2)

        val parcel = Parcel.obtain()
        parcel.writeInt(11)
        parcel.writeInt(22)
        parcel.setDataPosition(0)
        val r1 = parcel.readInt()
        val r2 = parcel.readInt()
        LogUtils.d("r1: $r1, r2: $r2")
    }
}