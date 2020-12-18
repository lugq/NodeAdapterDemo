package com.lugq.nodeadapterdemo.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lugq.nodeadapterdemo.R
import com.lugq.nodeadapterdemo.lesson.LessonActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 带有CheckBox
        btnTest1.setOnClickListener {
            startActivity(Intent(this, HasCheckBoxActivity::class.java))
        }

        // 点击内层的btn在Activity中监听回调
        btnTest2.setOnClickListener {
            startActivity(Intent(this, CommonActivity::class.java))
        }

        // 横向展示的
        btnTest3.setOnClickListener {
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

}