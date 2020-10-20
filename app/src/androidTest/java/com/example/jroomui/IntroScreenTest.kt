package com.example.jroomui

import android.app.Activity
import android.content.Intent
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class IntroScreenTest {

/*    @get: Rule
    var activityScenarioRule: ActivityScenarioRule<IntroActivity> =
        ActivityScenarioRule(IntroActivity::class.java)*/
    
    @get: Rule
    var activityScenarioRule: ActivityScenarioRule<IntroActivity> =
        ActivityScenarioRule(
            Intent(
                ApplicationProvider.getApplicationContext(),
                IntroActivity::class.java
            ).apply { putExtra("MyArgs", "Nothing") })
    
    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { it ->
            (it.findViewById<ConstraintLayout>(R.id.intro_cl))?.let {
            
            }
            (it.findViewById<TextView>(R.id.intro_logo))?.let {
                // it.text = "ChangeUI"
            }
        }
    }
    
    @After
    fun close() {
        activityScenarioRule.scenario.close()
    }
    
    @Test
    fun resultTest() {
        activityScenarioRule.scenario.onActivity {
            it.setResult(Activity.RESULT_OK, Intent().apply { putExtra("Result", "Ok") })
            it.finish()
        }
        Assert.assertEquals(activityScenarioRule.scenario.result.resultCode, Activity.RESULT_OK)
        val result = activityScenarioRule.scenario.result.resultData?.extras?.getString("Result")
        Assert.assertEquals(result, "Ok")
    }
    
    @Test
    fun moveToStateTest() {
        activityScenarioRule.scenario?.let {
            it.moveToState(Lifecycle.State.STARTED)
            Assert.assertEquals(it.state, Lifecycle.State.STARTED)
            it.moveToState(Lifecycle.State.CREATED)
            Assert.assertEquals(it.state, Lifecycle.State.CREATED)
            it.moveToState(Lifecycle.State.RESUMED)
            Assert.assertEquals(it.state, Lifecycle.State.RESUMED)
            it.moveToState(Lifecycle.State.DESTROYED)
            Assert.assertEquals(it.state, Lifecycle.State.DESTROYED)
            // activityScenario.recreate()
        }
    }

/*
    lateinit var activityScenario: ActivityScenario<IntroActivity>
    
    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(
            Intent(
                ApplicationProvider.getApplicationContext(),
                IntroActivity::class.java
            ).apply {
                putExtra("MyArgs", "Nothing")
            })
        
        activityScenario.onActivity {
            (it.findViewById<ConstraintLayout>(R.id.intro_cl))?.let {
            
            }
        }
    }
    
    @After
    fun close() {
        activityScenario.close()
    }
    
    @Test
    fun resultTest() {
        activityScenario.onActivity {
            it.setResult(Activity.RESULT_OK, Intent().apply { putExtra("Result", "Ok") })
            it.finish()
        }
        
        Assert.assertEquals(activityScenario.result.resultCode, Activity.RESULT_OK)
        val result =
            activityScenario.result.resultData?.extras?.getString("Result")
        Assert.assertEquals(result, "Ok")
    }
    
    @Test
    fun moveToStateTest() {
        activityScenario.moveToState(Lifecycle.State.STARTED)
        Assert.assertEquals(activityScenario.state, Lifecycle.State.STARTED)
        activityScenario.moveToState(Lifecycle.State.CREATED)
        Assert.assertEquals(activityScenario.state, Lifecycle.State.CREATED)
        activityScenario.moveToState(Lifecycle.State.RESUMED)
        Assert.assertEquals(activityScenario.state, Lifecycle.State.RESUMED)
        activityScenario.moveToState(Lifecycle.State.DESTROYED)
        Assert.assertEquals(activityScenario.state, Lifecycle.State.DESTROYED)
        // activityScenario.recreate()
    }
*/
}