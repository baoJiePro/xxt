package com.baojie.learn.jni_project_video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.baojie.learn.jni_project_video.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var player: FastPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player = FastPlayer()

        player.setDataSource("")

        player.setOnPreparedListener(object : FastPlayer.OnPreparedListener{
            override fun onPrepared() {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "准备成功，即将开始播放", Toast.LENGTH_SHORT).show()
                }
                player.start()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        player.prepare()
    }

    override fun onStop() {
        super.onStop()
        player.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

}