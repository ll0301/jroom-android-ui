package com.example.jroomui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
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
    private lateinit var loginTextView: TextView
    private lateinit var guestTextView: TextView
    
    private lateinit var cursorAnimation: LottieAnimationView
    private lateinit var swipeUpAnimation: LottieAnimationView
    private lateinit var swipeDownAnimation: LottieAnimationView
    
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
        
        swipeUpAnimation = view.findViewById(R.id.intro_swipe_up)
        swipeDownAnimation = view.findViewById(R.id.intro_swipe_down)
        swipeDownAnimation.visibility = View.GONE
        swipeUpAnimation.visibility = View.GONE
        
        parentView = view.findViewById(R.id.intro_cl)
        
        loginTextView = view.findViewById(R.id.intro_login_text)
        guestTextView = view.findViewById(R.id.intro_guest_text)
        
        var statusBarHeight: Int = 0
        var resId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resId)
        }
        
        setOnTouchListener(statusBarHeight)
    }
    
    
    @SuppressLint("ClickableViewAccessibility", "Recycle", "ResourceAsColor")
    private fun setOnTouchListener(status: Int) {
        logoSwipeButton.setOnTouchListener { button, event ->
            //var parentWidth = parentView.width
            var parentHeight = parentView.height
            when (event!!.action) {
                MotionEvent.ACTION_DOWN -> {
                    cursorAnimation.visibility = View.GONE
                    guestTextView.visibility = View.VISIBLE
                    loginTextView.visibility = View.VISIBLE
                    swipeDownAnimation.visibility = View.VISIBLE
                    swipeUpAnimation.visibility = View.VISIBLE
                    swipeDownAnimation.playAnimation()
                    swipeUpAnimation.playAnimation()
                }
    
                MotionEvent.ACTION_UP -> {
                    button.y = ((parentHeight * 0.5f) - status)
                    button.visibility = View.VISIBLE
                    cursorAnimation.visibility = View.VISIBLE
                    swipeDownAnimation.visibility = View.GONE
                    swipeUpAnimation.visibility = View.GONE
                    guestTextView.visibility = View.GONE
                    loginTextView.visibility = View.GONE
                    guestTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                    loginTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                }
    
                MotionEvent.ACTION_MOVE -> {
                    button.y = button.y + (event.y - (button.height * 0.5f))
                    button.visibility = View.VISIBLE
                    guestTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                    loginTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                    when {
                        button.y < (parentHeight * 0.1f) - status -> {
                            button.visibility = View.GONE
                            loginTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30f)
                        }
            
                        button.y > (parentHeight * 0.9f) - status -> {
                            button.visibility = View.GONE
                            guestTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30f)
                        }
            
                        button.y < (parentHeight * 0.45f) - status ||
                                button.y > (parentHeight * 0.55f) - status  -> {
                            swipeUpAnimation.visibility = View.GONE
                            swipeDownAnimation.visibility = View.GONE
                        }
                    }
                }
            }
            true
        }
    }
    
    
    
}