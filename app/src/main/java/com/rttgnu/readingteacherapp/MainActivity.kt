package com.rttgnu.readingteacherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moveButton = findViewById<Button>(R.id.movetestButton)
        val stButton = findViewById<Button>(R.id.speechtextButton)


        // 페이지 이동
        fun moveToPageMT() {
            val intent1 = Intent(this, movetestButton::class.java)
            startActivity(intent1)
        }
        fun moveToPageST(){
            val intent2 = Intent(this, SpeechTextActivity::class.java)
            startActivity(intent2)
        }
  0
        // 함수호출
        moveButton.setOnClickListener {
            moveToPageMT()
        }
        stButton.setOnClickListener {
            moveToPageST()
        }
    }
}