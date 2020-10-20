package com.example.jroom_anc_ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jroom_anc_ui.tutorials.edittext.CustomEditTextActivity
import com.example.jroom_anc_ui.tutorials.emotional.EmotionalActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        emotional_button.setOnClickListener {
            val intent = Intent(this@MainActivity, EmotionalActivity::class.java)
            startActivity(intent)
        }
        
        edit_text_button.setOnClickListener {
            val intent = Intent(this@MainActivity, CustomEditTextActivity::class.java)
            startActivity(intent)
        }
    }
}