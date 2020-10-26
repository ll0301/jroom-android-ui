package com.example.jroomui.introswipe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.airbnb.lottie.LottieAnimationView
import com.example.jroomui.R
import com.example.jroomui.main.MainActivity
import com.example.jroomui.util.showSnackBar
import com.google.android.material.snackbar.Snackbar

class IntroSwipeFragment : Fragment(), LifecycleObserver {
    
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
        
        return inflater.inflate(R.layout.fragment_intro_swipe, container, false)
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

/*    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreated() {
        // activity?.lifecycle?.removeObserver(this)
        // view?.showSnackbar("hi", 0)
    }
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.lifecycle?.addObserver(this)
    }
    
    override fun onDetach() {
        super.onDetach()
        activity?.lifecycle?.removeObserver(this)
    }*/
    
    
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
                    when {
                        button.y < (parentHeight * 0.1f) - status -> {
                            button.visibility = View.GONE
                            view?.showSnackBar("Coming soon ...", Snackbar.LENGTH_SHORT)
                        }
            
                        button.y > (parentHeight * 0.9f) - status -> {
                            val intent = Intent(activity, MainActivity::class.java)
                            startActivity(intent)
                            activity?.overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                            activity?.supportFragmentManager?.beginTransaction()?.remove(this)
                                ?.commit()
                            activity?.finish()
                        }
                    }
                    cursorAnimation.visibility = View.VISIBLE
                    swipeDownAnimation.visibility = View.GONE
                    swipeUpAnimation.visibility = View.GONE
                    guestTextView.visibility = View.GONE
                    loginTextView.visibility = View.GONE
                    guestTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                    loginTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                    button.y = ((parentHeight * 0.5f) - status)
                    button.visibility = View.VISIBLE
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
                            //view?.showSnackbar("hi", Snackbar.LENGTH_LONG)
                        }
            
                        button.y < (parentHeight * 0.45f) - status ||
                                button.y > (parentHeight * 0.55f) - status -> {
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