package com.example.jroomui.introswipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jroomui.R

class IntroSwipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_swipe)
        
        mainSwipeFragment()
    }
    
    private fun mainSwipeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, IntroSwipeFragment())
            .commit()
    }
}