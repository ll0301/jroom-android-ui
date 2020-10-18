package com.example.jroom_anc_ui.tutorials.emotional

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jroom_anc_ui.R
import kotlinx.android.synthetic.main.activity_emotional.*

class EmotionalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emotional)
    
        happyButton.setOnClickListener {
            emotionalFaceView.happinessState = EmotionalFaceView.HAPPY
        }
    
        sadButton.setOnClickListener {
            emotionalFaceView.happinessState = EmotionalFaceView.SAD
        }
    }
}