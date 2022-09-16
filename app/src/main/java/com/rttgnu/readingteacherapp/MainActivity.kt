package com.rttgnu.readingteacherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)








/*
        auth = FirebaseAuth.getInstance()
        val bt_logout: Button = findViewById(R.id.googleSignOutBtn)
        bt_logout.setOnClickListener {
            Firebase.auth.signOut();
            logoutSuccess()
        }
*/
    }
/*
    private fun logoutSuccess(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
*/
}