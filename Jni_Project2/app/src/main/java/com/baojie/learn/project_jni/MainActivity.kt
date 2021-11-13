package com.baojie.learn.project_jni

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.baojie.learn.project_jni.activity.SplashActivity
import com.baojie.learn.project_jni.databinding.ActivityMainBinding
import com.baojie.learn.project_jni.day19.JavaObjectActivity
import com.baojie.learn.project_jni.day23.ParcelActivity
import com.blankj.utilcode.util.ActivityUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.bt1.text = stringFromJNI()
        binding.bt1.setOnClickListener {
            ActivityUtils.startActivity(SplashActivity::class.java)
        }
        binding.bt2.setOnClickListener {
            ActivityUtils.startActivity(JavaObjectActivity::class.java)
        }
        binding.bt3.setOnClickListener {
            ActivityUtils.startActivity(ParcelActivity::class.java)
        }
    }

    /**
     * A native method that is implemented by the 'project_jni' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'project_jni' library on application startup.
        init {
            System.loadLibrary("project_jni")
        }
    }
}