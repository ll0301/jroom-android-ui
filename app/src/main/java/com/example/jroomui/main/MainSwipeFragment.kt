package com.example.jroomui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.jroomui.R

class MainSwipeFragment : Fragment() {
    
    private lateinit var parentView: ConstraintLayout
    private lateinit var logoSwipeButton: TextView
    private lateinit var cursorAnimation: LottieAnimationView
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        return inflater.inflate(R.layout.fragment_main_swipe, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        logoSwipeButton = view.findViewById(R.id.intro_logo)
        cursorAnimation = view.findViewById(R.id.intro_cursor)
        parentView = view.findViewById(R.id.intro_cl)
    
        var statusBarHeight:Int = 0
        var resId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resId)
        }
        
        setOnTouchListener(statusBarHeight)
    }
    

    
    @SuppressLint("ClickableViewAccessibility", "Recycle")
    private fun setOnTouchListener(status: Int) {
        logoSwipeButton.setOnTouchListener { v, event ->
            //var parentWidth = parentView.width
            var parentHeight = parentView.height
            when (event!!.action) {
                MotionEvent.ACTION_DOWN -> {
                    cursorAnimation.visibility = View.GONE
                }
                
                MotionEvent.ACTION_UP -> {
                    cursorAnimation.visibility = View.VISIBLE
                    v.y = ((parentHeight / 2) - status).toFloat()
                }
                
                MotionEvent.ACTION_MOVE -> {
                    when {
                        v.y < 0f -> {
                            v.y = 0f
                        }
                        (v.y + v.height) > parentHeight -> {
                            v.y = parentHeight.toFloat() - v.height
                        }
                        else -> {
                            v.y = v.y + (event.y - (v.height * 0.5f))
                        }
                    }
                }
            }
            true
        }
    }
}