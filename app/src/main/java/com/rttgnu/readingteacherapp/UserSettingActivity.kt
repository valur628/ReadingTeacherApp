package com.rttgnu.readingteacherapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rttgnu.readingteacherapp.databinding.ActivityUsersettingBinding

class UserSettingActivity: AppCompatActivity() {
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 바인딩 초기화 //
        val binding = ActivityUsersettingBinding.inflate(layoutInflater)
        // 2. 레이아웃 표시 //
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.googleSignOutBtn.setOnClickListener {
            Firebase.auth.signOut();
            logoutSuccess()
        }

    }

    private fun logoutSuccess(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}