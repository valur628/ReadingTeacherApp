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

        // 페이지 이동
        fun moveToAnotherPage() {
            val intent = Intent(this, movetestButton::class.java)
            startActivity(intent)
        }
  0
        // 함수호출
        moveButton.setOnClickListener {
            moveToAnotherPage()
        }
    }
}