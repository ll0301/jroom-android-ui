package com.example.jroomui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jroomui.main.MainActivity
import kotlinx.coroutines.*

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        
        var statusBarHeight:Int = 0;
        var resId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resId)
        }
        Log.e("barHeight", statusBarHeight.toString()) // result : 63
        
        val introLogo = findViewById<TextView>(R.id.intro_logo)
        introLogo.setPadding(0,statusBarHeight,0,0)
        
        val logoAnimation = AnimationUtils.loadAnimation(applicationContext,R.anim.fadein);
        introLogo.startAnimation(logoAnimation)
        
       CoroutineScope(Dispatchers.Main).launch {
           withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
               delay(4000L)
               val intent = Intent(this@IntroActivity, MainActivity::class.java)
               startActivity(intent)
               overridePendingTransition(R.anim.fadein, R.anim.fadeout)
               finish()
           }
       }
    }
}