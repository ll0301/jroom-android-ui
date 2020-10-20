package com.example.jroom_anc_ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        
        // Coroutine
       CoroutineScope(Dispatchers.Main).launch {
           withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
               delay(4000L)
               val intent = Intent(this@IntroActivity, MainActivity::class.java)
               startActivity(intent)
               finish()
           }
       }
    }
}