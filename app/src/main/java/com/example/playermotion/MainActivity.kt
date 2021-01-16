package com.example.playermotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.playermotion.ui.main.MainFragment
import com.example.playermotion.ui.main.PlayerFragment
import java.util.function.LongToDoubleFunction
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    lateinit var motionLayout: MotionLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()

            supportFragmentManager.beginTransaction()
                .replace(R.id.playerContainer, PlayerFragment.newInstance())
                .commitNow()
        }

        motionLayout = findViewById(R.id.mainMotionLayout)

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        supportFragmentManager.findFragmentById(R.id.playerContainer).also {
            if (it == null) {
                findViewById<FrameLayout>(R.id.playerContainer).visibility = View.GONE
                return@also
            }

            it as PlayerFragment

            motionLayout.apply {
                setTransitionListener(
                    object : MotionLayout.TransitionListener {
                        override fun onTransitionChange(
                            motionLayout: MotionLayout?,
                            startId: Int,
                            endId: Int,
                            progress: Float
                        ) {
                            it.playerMotionLayout.progress = abs(progress)
                        }

                        override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {

                        }

                        override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                        }

                        override fun onTransitionTrigger(
                            p0: MotionLayout?,
                            p1: Int,
                            p2: Boolean,
                            p3: Float
                        ) {
                        }
                    }
                )
            }
        }
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.playerContainer)).also {
            if (it == null) {
                super.onBackPressed()
                return
            }

            if (motionLayout.currentState == R.id.end) {
                motionLayout.transitionToStart()
            } else {
                super.onBackPressed()
            }
        }
    }
}