package com.example.jroomui

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        
        val introLogo = findViewById<TextView>(R.id.intro_logo)
        val logoAnimation = AnimationUtils.loadAnimation(applicationContext,R.anim.sequential);
        introLogo.startAnimation(logoAnimation)
        
       CoroutineScope(Dispatchers.Main).launch {
           withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
               delay(3500L)
               val intent = Intent(this@IntroActivity, MainActivity::class.java)
               startActivity(intent)
               finish()
           }
       }
    }
}